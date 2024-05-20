/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.common;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author KENSHIN
 */
@WebServlet(name = "Error", urlPatterns = {"/error"})
public class Error extends HttpServlet {

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
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
        String errorTitle;
        String errorMessage;
        if (statusCode != null) {
            switch (statusCode) {
                case HttpServletResponse.SC_NOT_FOUND:
                    errorTitle = "The page you looking for couldn't be found";
                    errorMessage = "The page you are looking for might have been removed, had its name changed, or is temporarily unavaiable.";
                    break;
                case HttpServletResponse.SC_FORBIDDEN:
                    errorTitle = "You do not have permission to access this resource";
                    errorMessage = "Make sure you have logged in with an account that has permission to access this resource. If you think this is an error, please contact the administratior.";
                    break;
                case HttpServletResponse.SC_SERVICE_UNAVAILABLE:
                    errorTitle = "This page is under development";
                    errorMessage = "Please try again later.";
                    break;
                default:
                    errorTitle = "Unknown error";
                    errorMessage = "Please report this to the administratior.";
                    break;
            }
        } else {
            errorTitle = "Unknown error";
            errorMessage = "Please report this to the administratior.";
        }

        request.setAttribute("errorCode", statusCode);
        request.setAttribute("errorTitle", errorTitle);
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("error.jsp").forward(request, response);
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
