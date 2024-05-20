/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.admin;

import dal.DBContext;
import dal.PageDAO;
import dal.SettingDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Page;
import model.Setting;

/**
 *
 * @author KENSHIN
 */
@WebServlet(name = "Pages List", urlPatterns = {"/pages-list"})
public class PageList extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //  Prepare parameters for pagination
        int currentPage;
        try {
            currentPage = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException ex) {
            currentPage = 1;
        }
        int numOfRecords;
        try {
            numOfRecords = Integer.parseInt(request.getParameter("numOfRecords"));
        } catch (NumberFormatException ex) {
            numOfRecords = 5;
        }
        //  Prepare filters for query command.
        List<Map<Object, String>> filterList = new ArrayList<>();
        String[] selectedActionIds = request.getParameterValues("selectedActions");
        if (selectedActionIds != null) {
            Map<Object, String> filters = new HashMap<>();
            for (String actionId : selectedActionIds) {
                filters.put(Integer.parseInt(actionId), "ActionTypeSettingId");
            }
            filterList.add(filters);
        }
        String[] selectedObjectIds = request.getParameterValues("selectedObjs");
        if (selectedObjectIds != null) {
            Map<Object, String> filters = new HashMap<>();
            for (String objectId : selectedObjectIds) {
                filters.put(Integer.parseInt(objectId), "ObjectSettingId");
            }
            filterList.add(filters);
        }
        Integer selectedStatus;
        try {
            selectedStatus = Integer.parseInt(request.getParameter("status"));
            switch (selectedStatus) {
                case -1: {
                    break;
                }
                case 1: {
                    Map<Object, String> filters = new HashMap<>();
                    filters.put(true, "Status");
                    filterList.add(filters);
                    break;
                }
                case 0: {
                    Map<Object, String> filters = new HashMap<>();
                    filters.put(false, "Status");
                    filterList.add(filters);
                    break;
                }
                default: {
                    selectedStatus = 1;
                    break;
                }
            }

        } catch (NumberFormatException ex) {
            selectedStatus = 1;
        }
        //  Get a list of pages that is accessible by this list of roles.
        String[] selectedRoleIds = request.getParameterValues("selectedRoles");
        if (selectedRoleIds != null) {
            Map<Object, String> filters = new HashMap<>();
            List<Integer> roles = new ArrayList<>();
            for (String role : selectedRoleIds) {
                roles.add(Integer.parseInt(role));
            }
            List<Integer> pageIds = new PageDAO().pagesByRoles(roles);
            if (pageIds != null) {
                for (Integer pageId : pageIds) {
                    filters.put(pageId, "Id");
                }
            }
            filterList.add(filters);
        }
        //  Get a list of Pages matching the specified criteria
        List<Page> pages = new PageDAO().getList((currentPage - 1) * numOfRecords, numOfRecords, filterList);
        request.setAttribute("data", pages);
        //  
        List<List<Integer>> authorizedFor = new ArrayList<>();
        for (Page f : pages) {
            authorizedFor.add(new PageDAO().rolesByPage(f.getId()));
        }
        request.setAttribute("authorizedFor", authorizedFor);
        //  Set parameters for pagination
        int numberAllRecords = new PageDAO().numberOfRecords(filterList);
        int maxPage = numberAllRecords / numOfRecords;
        if (numberAllRecords % numOfRecords != 0) {
            maxPage++;
        }
        if (currentPage > maxPage) {
            currentPage = 1;
        }
        request.setAttribute("page", currentPage);
        request.setAttribute("max", maxPage);
        request.setAttribute("numOfRecords", numOfRecords);
        //  Prepare data for the filter board
        List<Setting> roles = new SettingDAO().getByName("Role");
        //  Sort roles in order
        roles.sort((setting1, setting2) -> {
            return Integer.compare(setting1.getOrder(), setting2.getOrder());
        });
        List<Setting> actions = new SettingDAO().getByName("Action");
        List<Setting> objects = new SettingDAO().getByName("Objective");
        request.setAttribute("roles", roles);
        request.setAttribute("actions", actions);
        request.setAttribute("objectives", objects);
        //  Keep filtered options
        request.setAttribute("selectedActions", selectedActionIds);
        request.setAttribute("selectedObjs", selectedObjectIds);
        request.setAttribute("status", selectedStatus);
        request.setAttribute("selectedRoles", selectedRoleIds);
        //  Finish
        request.getRequestDispatcher("admin/pages-list.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
