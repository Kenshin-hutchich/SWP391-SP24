/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.common;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import model.User;
import services.IMailService;
import services.MailService;
import ultility.Validate;

/**
 *
 * @author KENSHIN
 */
@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {

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
            out.println("<title>Servlet Register</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("register.jsp").forward(request, response);
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
        IMailService mailService = new MailService();
        HttpSession session = request.getSession();
        String rem = request.getParameter("email");
        String rpa = request.getParameter("password");
        String repa = request.getParameter("repassword");
        String rna = request.getParameter("name");
        String rph = request.getParameter("phone");
        String gender = request.getParameter("gender");
        boolean reg = Boolean.parseBoolean(gender);
        String code = String.valueOf((int) ((Math.random() * (999999 - 100000)) + 100000));
        User a = new Validate().checkExistedEmail(rem);
        User b = new Validate().checkExistedPhone(rph);

        if (rem.isEmpty() || rpa.isEmpty() || repa.isEmpty() || rna.isEmpty() || rph.isEmpty() || gender.isEmpty()) {
//                        request.setAttribute("mess", "Account Exist!");
            try ( PrintWriter out = response.getWriter()) {
                out.print("fail");
            }
        } else if (a == null & b == null) {
            if (repa.equalsIgnoreCase(rpa)) {

                try {
                    try ( PrintWriter out = response.getWriter()) {
                        out.print("success");
                    }
                    mailService.SendConfirmationCode(rem, code);
                } catch (MessagingException ex) {
                    Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
                }

                long currentTime = System.currentTimeMillis();
                session.setAttribute("verificationCode", code);
                session.setAttribute("email", rem);
                session.setAttribute("password", rpa);
                session.setAttribute("name", rna);
                session.setAttribute("phone", rph);
                session.setAttribute("gender", reg);
//                        request.getRequestDispatcher("verifyregister.jsp").forward(request, response);
                session.setAttribute("verificationTime", currentTime);
            } else {
                try ( PrintWriter out = response.getWriter()) {
                    out.print("fail");
                }
            }

        } else if (rem.isEmpty() || rpa.isEmpty() || rna.isEmpty() || rph.isEmpty() || gender.isEmpty()) {
//                        request.setAttribute("mess", "Account Exist!");
            try ( PrintWriter out = response.getWriter()) {
                out.print("fail");
            }
        }
        try ( PrintWriter out = response.getWriter()) {
            out.print("fail");
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
