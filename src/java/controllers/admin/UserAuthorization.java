/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.admin;

import dal.PageDAO;
import dal.SettingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Page;
import model.Setting;

/**
 *
 * @author KENSHIN
 */
@WebServlet(name = "UserAuthorization", urlPatterns = {"/user-authorization"})
public class UserAuthorization extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserAuthorization</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserAuthorization at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        SettingDAO settingDAO = new SettingDAO();
        PageDAO pageDAO = new PageDAO();

        //  Get selected role id, set it to 1 if none is selected.
        int selectedRoleId;
        try {
            selectedRoleId = Integer.parseInt(request.getParameter("role"));
        } catch (NumberFormatException ex) {
            selectedRoleId = 1;
        }
        //  Prepare data for the role select and sort role by order
        List<Setting> roles = settingDAO.getByName("Role");
        roles.sort((setting1, setting2) -> {
            return Integer.compare(setting1.getOrder(), setting2.getOrder());
        });
        request.setAttribute("roles", roles);
        //  Prepare data for the table
        List<Setting> objects = settingDAO.getByName("Objective");
        List<Page> pages = pageDAO.getAll();
        
        List<Setting> allRoles = settingDAO.getAll();
        List<Integer> lowerOrderRoleIds = new ArrayList();
        for (Setting role : allRoles) {
            if (role.getOrder() > settingDAO.getById(selectedRoleId).getOrder()){
                lowerOrderRoleIds.add(role.getId());
            }
        }
        
        List<Integer> permissions = pageDAO.rolePermissions(selectedRoleId, lowerOrderRoleIds);
        //  Keep filtered options
        request.setAttribute("selectedRole", selectedRoleId);
        request.setAttribute("objectives", objects);
        request.setAttribute("pages", pages);
        request.setAttribute("permissions", permissions);
        //  Close connections 
        settingDAO.closeConnection();
        pageDAO.closeConnection();
        request.getRequestDispatcher("admin/user-authorization.jsp").forward(request, response);
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
        PageDAO pageDAO = new PageDAO();
        SettingDAO settingDAO = new SettingDAO();
        int selectedPage = Integer.parseInt(request.getParameter("pageId"));
        int selectedRole = Integer.parseInt(request.getParameter("roleId"));
        int roleOrder = settingDAO.getById(selectedRole).getOrder();
        String hasAccess = request.getParameter("hasAccess");
        //  Grant access permission for selected role
        if (hasAccess != null && hasAccess.equals("on")) {
            if (!pageDAO.updatePermission(selectedRole, selectedPage, true)) {
                pageDAO.closeConnection();
                settingDAO.closeConnection();
                try ( PrintWriter out = response.getWriter()) {
                    out.print("failed");
                }
                return;
            }
            //  Grant the same access permission for role with higher priority (if any)
            if (roleOrder > 1) {
                for (int i = 1; i < roleOrder; i++) {
                    List<Setting> roles = settingDAO.getByNameAndOrder("Role", i);
                    for (Setting role : roles) {
                        if (!pageDAO.updatePermission(role.getId(), selectedPage, true)) {
                            pageDAO.closeConnection();
                            settingDAO.closeConnection();
                            try ( PrintWriter out = response.getWriter()) {
                                out.print("failed");
                            }
                            return;
                        }
                    }
                }
            }
            pageDAO.closeConnection();
            settingDAO.closeConnection();
            try ( PrintWriter out = response.getWriter()) {
                out.print("success");
            }
        } else {
            //  Revoke access permission for selected role
            if (!pageDAO.updatePermission(selectedRole, selectedPage, false)) {
                pageDAO.closeConnection();
                settingDAO.closeConnection();
                try ( PrintWriter out = response.getWriter()) {
                    out.print("failed");
                }
                return;
            }
            //  Revoke the same access permission for role with lower priority (if any)
            int maxOrder = settingDAO.getMaxOrderByName("Role");
            if (roleOrder < maxOrder) {
                for (int i = roleOrder + 1; i <= maxOrder; i++) {
                    List<Setting> roles = settingDAO.getByNameAndOrder("Role", i);
                    for (Setting role : roles) {
                        if (!pageDAO.updatePermission(role.getId(), selectedPage, false)) {
                            pageDAO.closeConnection();
                            settingDAO.closeConnection();
                            try ( PrintWriter out = response.getWriter()) {
                                out.print("failed");
                            }
                            return;
                        }
                    }
                }
            }
            pageDAO.closeConnection();
            settingDAO.closeConnection();
            try ( PrintWriter out = response.getWriter()) {
                out.print("success");
            }
        }
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
