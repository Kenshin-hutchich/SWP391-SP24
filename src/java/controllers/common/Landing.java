/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.common;

import dal.CommentDAO;
import dal.CourseDAO;
import dal.CourseReviewDAO;
import dal.PostDAO;
import dal.SettingDAO;
import dal.UserDAO;
import dtos.CourseListDto;
import dtos.PostListDto;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Course;
import model.Post;
import model.Setting;

/**
 *
 * @author KENSHIN
 */
@WebServlet(name = "Landing", urlPatterns = {"/landing"}, loadOnStartup = 1)
public class Landing extends HttpServlet {

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
        CourseDAO courseDAO = new CourseDAO();
        List<Integer> topCourseIds = courseDAO.getTopCourseId(6);
        List<CourseListDto> courses = new ArrayList<>();
        for (int id : topCourseIds) {
            courses.add(toCourseListDto(courseDAO.getById(id)));
        }

        int courseCount = courseDAO.getTotal();
        int unitCount = 0;
        while (courseCount > 1000) {
            courseCount /= 1000;
            unitCount++;
            if (unitCount == 3) {
                break;
            }
        }
        String courseCountUnit = "";
        switch (unitCount) {
            case 1:
                courseCountUnit = "K";
                break;
            case 2:
                courseCountUnit = "M";
                break;
            case 3:
                courseCountUnit = "B";
                break;
        }

        UserDAO userDAO = new UserDAO();
        SettingDAO settingDAO = new SettingDAO();
        Setting studentRoleSetting = settingDAO.getByNameAndValue("Role", "Student");
        int userCount = userDAO.getTotal(studentRoleSetting);
        unitCount = 0;
        while (userCount > 1000) {
            userCount /= 1000;
            unitCount++;
            if (unitCount == 3) {
                break;
            }
        }
        String userCountUnit = "";
        switch (unitCount) {
            case 1:
                userCountUnit = "K";
                break;
            case 2:
                userCountUnit = "M";
                break;
            case 3:
                userCountUnit = "B";
                break;
        }

        PostDAO postDAO = new PostDAO();
        CommentDAO commentDAO = new CommentDAO();
        List<PostListDto> blogs = new ArrayList<>();
        for (Post post : postDAO.getListByPage(0, 6)) {
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

        userDAO.closeConnection();
        settingDAO.closeConnection();
        courseDAO.closeConnection();
        postDAO.closeConnection();
        commentDAO.closeConnection();
        
        request.setAttribute("courses", courses);
        request.setAttribute("posts", blogs);
        request.setAttribute("userCount", userCount);
        request.setAttribute("userCountUnit", userCountUnit);
        request.setAttribute("courseCount", courseCount);
        request.setAttribute("courseCountUnit", courseCountUnit);
        request.getRequestDispatcher("home.jsp").forward(request, response);
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

    private CourseListDto toCourseListDto(Course c) {
        CourseDAO courseDAO = new CourseDAO();
        CourseReviewDAO courseReviewDAO = new CourseReviewDAO();
        CourseListDto course = new CourseListDto();
        course.setId(c.getId());
        course.setOwner(c.getOwner());
        course.setTitle(c.getTitle());
        course.setThumbnail(c.getThumbnail());
        course.setDimension(c.getDimension());
        course.setDescription(c.getDescription());
        course.setStatus(c.getStatus());
        course.setFeatured(c.isFeatured());
        course.setNumberOfParticipants(c.getNumberOfParticipants());

        double averageRating = courseReviewDAO.getAverageRatingByCourseId(c.getId());
        averageRating = (double) Math.round(averageRating * 10) / 10.0;
        int ratingCount = courseReviewDAO.getCountOfReviewsByCourseId(c.getId());

        course.setCurrentParticipants(courseDAO.numberOfCurrentRegistration(c.getId()));
        course.setNumberOfRating(ratingCount);
        course.setRating(averageRating);
        courseReviewDAO.closeConnection();
        courseDAO.closeConnection();
        return course;
    }
}
