/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.common;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import model.User;
import services.HashService;
import services.IHashService;
import ultility.Hashpassword;
import ultility.SendEmail;
import ultility.Validate;

/**
 *
 * @author tudo7
 */
@WebServlet(name = "userController", urlPatterns = {"/usercontroller"})
public class userController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User u = new User();
        UserDAO us = new UserDAO();
        SendEmail ems = new SendEmail();
        Hashpassword h = new Hashpassword();
        IHashService hashService = new HashService();

        if (action != null) {
            switch (action) {
                case "verify":
                    String regverify = request.getParameter("code");
                    String regverificationCode = (String) session.getAttribute("verificationCode");
                    long verificationTime = (long) session.getAttribute("verificationTime");

                    // Check if verification code is correct
                    if (regverify.equals(regverificationCode)) {
                        // Check if the verification code is still valid (within 60 seconds)

                        try ( PrintWriter out = response.getWriter()) {
                            out.print("success");
                        }

                        String veremail = (String) session.getAttribute("email");
                        String verpassword = (String) session.getAttribute("password");
                        String vername = (String) session.getAttribute("name");
                        String verphone = (String) session.getAttribute("phone");
                        Boolean vergender = (Boolean) session.getAttribute("gender");
                        us.register(veremail, hashService.HashPassword(verpassword), vername, verphone, vergender);

                    } else {
                        try ( PrintWriter out = response.getWriter()) {
                            out.print("fail");
                        }

//                            request.getRequestDispatcher("verifyregister.jsp").forward(request, response);
                    }

                    break;
                case "verifyforgot":
                    String vem = request.getParameter("email");
                    String vecode = String.valueOf((int) ((Math.random() * (999999 - 100000)) + 100000));
                    long currentTime = System.currentTimeMillis();

                    try {
                        ems.sendEmail(vem, vecode);
                    } catch (MessagingException ex) {
                        Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    session.setAttribute("email", vem);
                    session.setAttribute("vecode", vecode);
                    session.setAttribute("verificationTime", currentTime);
                    request.getRequestDispatcher("verifyforgot.jsp").forward(request, response);

                    break;

                case "fverify":
                    String fcode = request.getParameter("code");
                    String vefcode = (String) session.getAttribute("vecode");
                    long afverificationTime = (long) session.getAttribute("verificationTime");

                    // Check if the verification code is correct
                    if (fcode.equals(vefcode)) {

                        // Check if the verification code is still valid (within 60 seconds)
                        long currentTimes = System.currentTimeMillis();
                        long elapsedTime = TimeUnit.MILLISECONDS.toSeconds(currentTimes - afverificationTime);

                        if (elapsedTime <= 60) {
                            try ( PrintWriter out = response.getWriter()) {
                                out.print("success");
                            }

//                            request.getRequestDispatcher("resetpass.jsp").forward(request, response);
                        } else {
                            try ( PrintWriter out = response.getWriter()) {
                                out.print("fail");
                            }
                            request.setAttribute("mess", "Verification code has expired.");
//                            request.getRequestDispatcher("verifyforgot.jsp").forward(request, response);
                        }
                    } else {
                        try ( PrintWriter out = response.getWriter()) {
                            out.print("fail");
                        }
                        request.setAttribute("mess", "Invalid verification code.");
//                        request.getRequestDispatcher("verifyforgot.jsp").forward(request, response);
                    }
                    break;
                case "changepassword":
                    String cmail = (String) session.getAttribute("email");
                    String cpass = request.getParameter("password");
                    String crpass = request.getParameter("repassword");

                    if (cpass.equalsIgnoreCase(crpass)) {
                        try ( PrintWriter out = response.getWriter()) {
                            out.print("success");
                        }
                        us.resetPass(h.hashPassword(cpass), cmail);
                        request.getRequestDispatcher("login.jsp").forward(request, response);

                    } else {
                        try ( PrintWriter out = response.getWriter()) {
                            out.print("fail");
                        }
                    }

                    break;
                default:
                    throw new AssertionError();
            }

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
