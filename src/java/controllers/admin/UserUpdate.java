/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.admin;

import dal.SettingDAO;
import dal.UserDAO;
import dtos.UserUpdateDto;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;
import model.Setting;
import model.User;

/**
 *
 * @author KENSHIN
 */
@WebServlet(name = "UserUpdate", urlPatterns = {"/user-update"})
public class UserUpdate extends HttpServlet {

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
            out.println("<title>Servlet UserUpdate</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserUpdate at " + request.getContextPath() + "</h1>");
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
        UserDAO userDAO = new UserDAO();
        SettingDAO settingDAO = new SettingDAO();

        int userId = Integer.parseInt(request.getParameter("id"));
        User user = userDAO.getById(userId);
        UserUpdateDto userUpdateDto = new UserUpdateDto(user);

        List<Setting> roles = settingDAO.getByName("Role");
        //  Remove the guest role
        for (Setting role : roles) {
            if (role.getValue().equalsIgnoreCase("Guest")){
                roles.remove(role);
                break;
            }
        }

        settingDAO.closeConnection();
        userDAO.closeConnection();

        request.setAttribute("user", userUpdateDto);
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("admin/user-update.jsp").forward(request, response);
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
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String mobile = request.getParameter("mobile");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        int roleId = Integer.parseInt(request.getParameter("roleId"));

        SettingDAO settingDAO = new SettingDAO();
        UserUpdateDto user = new UserUpdateDto(id, name, gender, mobile, new Timestamp(System.currentTimeMillis()), status, settingDAO.getById(roleId));
        settingDAO.closeConnection();

        UserDAO userDAO = new UserDAO();
        if (userDAO.updateUserProfile(user)) {
            try ( PrintWriter out = response.getWriter()) {
                out.println("success");
            }
        } else {
            try ( PrintWriter out = response.getWriter()) {
                out.println("fail");
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
