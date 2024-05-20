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
import java.util.ArrayList;
import java.util.List;
import model.Comment;
import model.Log;

import model.Post;
import model.Setting;

/**
 *
 * @author KENSHIN
 */
@WebServlet(name = "PostDetails", urlPatterns = {"/blog-details"})
public class PostDetails extends HttpServlet {

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
            out.println("<title>Servlet PostDetails</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PostDetails at " + request.getContextPath() + "</h1>");
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
        PostDAO postDAO = new PostDAO();
        LogDAO logDAO = new LogDAO();
        CommentDAO commentDAO = new CommentDAO();
        
        
        
        //  Get post id
        String postId = request.getParameter("id");
        int id = -1;
        try {
            id = Integer.parseInt(postId);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        //  Get requested post by ID
        Post post = postDAO.getById(id);
        if (post.getId() == 0) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        //  Get created time of the post
        Setting createSett = settingDAO.getByNameAndValue("Action", "Create");
        Setting postSett = settingDAO.getByNameAndValue("Objective", "Post");
        Log createdTime = new Log();
        for (Log log : logDAO.getByAction(createSett)) {
            if (log.getTargetId() == post.getId()
                    && log.getTargetTypeSetting().getId() == postSett.getId()) {
                createdTime = log;
                break;
            }
        }
        //  Prepare the main content of the post
        String[] paragraphs = post.getDescription().split("\\r?\\n");
        //  Get comments of the post
        List<Comment> comments = commentDAO.getByPostId(id);
        //  Get posts for recent posts section
        List<Post> recentPosts = postDAO.getListByPage(0, 3);
        List<Log> recentLogs = new ArrayList<>();
        for (Post p : recentPosts) {
            recentLogs.add(logDAO.getSpecific(p.getAuthor().getId(), createSett.getId(), postSett.getId(), p.getId()));
        }
        List<Integer> recentCmts = new ArrayList<>();
        for (Post p : recentPosts) {
            recentCmts.add(commentDAO.getByPostId(p.getId()).size());
        }
        
        settingDAO.closeConnection();
        postDAO.closeConnection();
        logDAO.closeConnection();
        commentDAO.closeConnection();

        request.setAttribute("recentPosts", recentPosts);
        request.setAttribute("recentLogs", recentLogs);
        request.setAttribute("recentCmts", recentCmts);
        request.setAttribute("comments", comments);
        request.setAttribute("blog", post);
        request.setAttribute("createdTime", createdTime);
        request.setAttribute("paragraphs", paragraphs);
        request.getRequestDispatcher("/blog-details.jsp").forward(request, response);
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
