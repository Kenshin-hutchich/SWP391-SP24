/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.admin;

import dal.SettingDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.mail.MessagingException;
import model.Setting;
import model.User;
import services.HashService;
import services.IHashService;
import services.IMailService;
import services.MailService;

/**
 *
 * @author KENSHIN
 */
@WebServlet(name = "AddUser", urlPatterns = {"/add-user"})
public class AddUser extends HttpServlet {

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
            out.println("<title>Servlet AddUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddUser at " + request.getContextPath() + "</h1>");
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
        List<Setting> roles = settingDAO.getByName("Role");
        //  Remove the guest role
        for (Setting role : roles) {
            if (role.getValue().equalsIgnoreCase("Guest")) {
                roles.remove(role);
                break;
            }
        }
        request.setAttribute("roles", roles);
        settingDAO.closeConnection();
        request.getRequestDispatcher("admin/add-user.jsp").forward(request, response);
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
        UserDAO userDAO = new UserDAO();
        String email = request.getParameter("email");
        if (userDAO.isEmailExisted(email)) {
            userDAO.closeConnection();
            try ( PrintWriter out = response.getWriter()) {
                out.println("failEmail");
            }
            return;
        }

        String mobile = request.getParameter("mobile");
        if (!mobile.isEmpty() && userDAO.isMobileExisted(mobile)) {
            userDAO.closeConnection();
            try ( PrintWriter out = response.getWriter()) {
                out.println("failMobile");
            }
            return;
        }

        String genderStr = request.getParameter("gender");
        boolean gender = false;
        if (genderStr != null) {
            switch (genderStr) {
                case "true":
                    gender = true;
                    break;
                case "false":
                    gender = false;
                    break;
            }
        } else {
            try ( PrintWriter out = response.getWriter()) {
                out.println("failGender");
            }
            return;
        }

        int roleId = Integer.parseInt(request.getParameter("roleId"));
        SettingDAO settingDAO = new SettingDAO();
        Setting roleSetting = settingDAO.getById(roleId);

        String name = request.getParameter("name");

        IHashService hashService = new HashService();
        String randomPasw = hashService.GenerateRandomString(8);
        String hash = hashService.HashPassword(randomPasw);

        IMailService mailService = new MailService();
        try {
            mailService.SendPassword(email, randomPasw);
        } catch (MessagingException | UnsupportedEncodingException ex) {
            userDAO.closeConnection();
            try ( PrintWriter out = response.getWriter()) {
                out.println("failSendPasw");
            }
            return;
        }

        User user = new User();
        user.setEmail(email);
        user.setGender(gender);
        user.setHash(hash);
        user.setMobile(mobile);
        user.setName(name);
        user.setRoleSetting(roleSetting);
        user.setStatus(true);
        Boolean insertStatus = userDAO.insert(user);
        if (insertStatus) {
            try ( PrintWriter out = response.getWriter()) {
                out.println("success");
            }
        } else {
            try ( PrintWriter out = response.getWriter()) {
                out.println("failInsert");
            }
        }
        settingDAO.closeConnection();
        userDAO.closeConnection();
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
