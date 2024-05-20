/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.common;

import dal.CommentDAO;
import dal.PostDAO;
import dtos.PostListDto;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Post;

/**
 *
 * @author KENSHIN
 */
@WebServlet(name = "PostsList", urlPatterns = {"/blogs-list"})
public class PostsList extends HttpServlet {

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
            out.println("<title>Servlet BlogsList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogsList at " + request.getContextPath() + "</h1>");
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
        PostDAO postDAO = new PostDAO();
        CommentDAO commentDAO = new CommentDAO();
        
        // Number of Posts per page
        int numOfPostPerPage = 6;
        // Get number of pages
        int numOfPost = postDAO.numberOfPosts();
        int maxPage = (numOfPost / numOfPostPerPage) + 1;
        // Current page number
        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            page = 1;
        }
        // Prevent page index out of bound
        if (page < 1) page = 1;
        if (page > maxPage) page = maxPage;
        // Get Posts based on page number and their corresponding logs and number of comments
        int startIndex = (page - 1) * numOfPostPerPage;
        int endIndex = Math.min(page * numOfPostPerPage, numOfPost);
        List<Post> postData = postDAO.getListByPage(startIndex, endIndex);
        List<PostListDto> blogs = new ArrayList<>();
        if (postData != null && !postData.isEmpty()) {
            for (Post post : postData) {
                PostListDto blog = new PostListDto();
                blog.setId(post.getId());
                blog.setAuthor(post.getAuthor());
                blog.setTitle(post.getTitle());
                blog.setBriefInfo(post.getBriefInfo());
                blog.setThumbnail(post.getThumbnail());
                blog.setNumberOfComment(commentDAO.getByPostId(post.getId()).size());
                blog.setCreatedAt(post.getCreatedAt());
                blog.setFeatured(post.isFeatured());
                blogs.add(blog);
            }
        }

        request.setAttribute("max", maxPage);
        request.setAttribute("page", page);
        request.setAttribute("data", blogs);
        
        postDAO.closeConnection();
        commentDAO.closeConnection();
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/blogs-list.jsp");
        dispatcher.forward(request, response);
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
