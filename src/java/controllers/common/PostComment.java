/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.common;

import dal.CommentDAO;
import dal.LogDAO;
import dal.PostDAO;
import dal.SettingDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import model.Comment;
import model.Log;
import model.Setting;

/**
 *
 * @author KENSHIN
 */
@WebServlet(name = "PostComment", urlPatterns = {"/post-comment"})
public class PostComment extends HttpServlet {

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
                SettingDAO settingDAO = new SettingDAO();
        PostDAO postDAO = new PostDAO();
        LogDAO logDAO = new LogDAO();
        UserDAO userDAO = new UserDAO();
        CommentDAO commentDAO = new CommentDAO();
        
        Integer postId = Integer.parseInt(request.getParameter("postId"));
        Integer UID = Integer.parseInt(request.getParameter("UID"));
        String comment = request.getParameter("comment");
        Comment cmt = new Comment();
        cmt.setAuthor(userDAO.getById(UID));
        cmt.setPost(postDAO.getById(postId));
        cmt.setContent(comment);
        
        settingDAO.closeConnection();
        postDAO.closeConnection();
        logDAO.closeConnection();        
        userDAO.closeConnection();
        commentDAO.closeConnection();
        
        response.sendRedirect(request.getContextPath() + "/blog-details?id=" + postId);
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
