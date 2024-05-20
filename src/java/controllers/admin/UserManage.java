/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.admin;

import dal.SettingDAO;
import dal.UserDAO;
import dtos.UserListDto;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Setting;
import model.User;

/**
 *
 * @author Dell
 */
@WebServlet(name = "UserManage", urlPatterns = {"/user-manage"})
public class UserManage extends HttpServlet {

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
            out.println("<title>Servlet AUserControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AUserControl at " + request.getContextPath() + "</h1>");
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
        //  Get current page, set to 1 if there's no data
        int currentPage;
        try {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        } catch (NumberFormatException ex) {
            currentPage = 1;
        }
        //  Display 6 record per page
        int tableSize = 6;
        //  ----------------------------Get Filter Options-----------------------------
        //  Search by role
        SettingDAO settingDAO = new SettingDAO();
        int searchRoleId;
        try {
            searchRoleId = Integer.parseInt(request.getParameter("searchRoleId"));
        } catch (NumberFormatException ex) {
            searchRoleId = settingDAO.getByNameAndValue("Role", "Student").getId();
        }
        //  Search by status (include blocked user or not)
        String searchStatus = request.getParameter("searchStatus");
        Boolean statusValue = null;
        if (searchStatus != null && !searchStatus.isEmpty()) {
            switch (searchStatus) {
                case "true":
                    statusValue = Boolean.TRUE;
                    break;
                case "false":
                    statusValue = Boolean.FALSE;
                    break;
                default:
                    searchStatus = null;
                    break;
            }
        } else {
            searchStatus = "true";
            statusValue = Boolean.TRUE;
        }
        //  Search by time
        Timestamp startTimestamp;
        Timestamp endTimestamp;
        String searchStartDate = request.getParameter("searchStartDate");
        String searchEndDate = request.getParameter("searchEndDate");
        try {
            LocalDate startDate = LocalDate.parse(searchStartDate);
            LocalDate endDate = LocalDate.parse(searchEndDate);
            LocalDateTime startDateTime = startDate.atStartOfDay();
            LocalDateTime endDateTime = endDate.plusDays(1).atStartOfDay().minusNanos(1);
            startTimestamp = Timestamp.valueOf(startDateTime);
            endTimestamp = Timestamp.valueOf(endDateTime);
        } catch (Exception ex) {
            startTimestamp = null;
            searchEndDate = LocalDate.now().toString();
            endTimestamp = new Timestamp(System.currentTimeMillis());
        }
        int startIndex = (currentPage - 1) * tableSize;

        UserDAO userDAO = new UserDAO();
        List<User> userList = userDAO.getPaginated(startIndex, tableSize, searchRoleId, statusValue, startTimestamp, endTimestamp);
        int maxPage = userDAO.getSize(searchRoleId, statusValue, startTimestamp, endTimestamp);
        if (maxPage % tableSize == 0){
            maxPage = maxPage / tableSize;
        } else {
            maxPage = maxPage / tableSize + 1;
        }
        
        userDAO.closeConnection();
        List<UserListDto> users = new ArrayList<>();
        for (User u : userList) {
            users.add(new UserListDto(u));
        }

        List<Setting> roles = settingDAO.getByName("Role");
        //  Remove the guest role
        for (Setting role : roles) {
            if (role.getValue().equalsIgnoreCase("Guest")){
                roles.remove(role);
                break;
            }
        }
        settingDAO.closeConnection();

        request.setAttribute("currentPage", currentPage);
        request.setAttribute("searchStatus", searchStatus);
        request.setAttribute("searchStartDate", searchStartDate);
        request.setAttribute("searchEndDate", searchEndDate);
        request.setAttribute("searchRoleId", searchRoleId);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("maxPage", maxPage);
        request.setAttribute("users", users);
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("/admin/user-list.jsp").forward(request, response);
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
