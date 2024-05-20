/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.common;

import dal.SettingDAO;
import dal.UserDAO;
import dtos.UserSessionDto;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Setting;
import model.User;

/**
 *
 * @author KENSHIN
 */
@WebServlet(name = "Home", urlPatterns = {"/home"})
public class Home extends HttpServlet {

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
        //  Get user information from current session
        UserSessionDto userSessionDto = new UserSessionDto();
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession(false);
        try {
            //  This line will throw NullPointerException if cookies is null
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    String sessionID = cookie.getValue();
                    //  This line will throw NullPointerException if current session is null
                    if (sessionID.equals(session.getId())) {
                        //  Throw a NullPointerException if session doesn't store user information
                        userSessionDto = (UserSessionDto) session.getAttribute("userSessionDto");
                        if (userSessionDto == null) {
                            throw new NullPointerException();
                        }
                    }
                }
            }
        } catch (NullPointerException ex) {
            //  If any of those cases happen, it means user haven't login yet. Return 403 Forbidden Error
            System.out.println(ex.getMessage());
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        UserDAO userDAO = new UserDAO();
        User u = userDAO.getById(userSessionDto.getId());
        userDAO.closeConnection();
        SettingDAO settingDAO = new SettingDAO();
        Setting adminRoleSetting = settingDAO.getByNameAndValue("Role", "Admin");
        Setting expertRoleSetting = settingDAO.getByNameAndValue("Role", "Expert");
        settingDAO.closeConnection();
        if (u.getRoleSetting().getId() == adminRoleSetting.getId()) {
            response.sendRedirect(request.getContextPath() + "/dashboard");
        }
        if (u.getRoleSetting().getId() == expertRoleSetting.getId()) {
            response.sendRedirect(request.getContextPath() + "/my-courses");
        }
        response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
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
        processRequest(request, response);
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
