/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.common;

import dal.CourseDAO;
import dal.CourseReviewDAO;
import dal.DimensionDAO;
import dtos.CourseListDto;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Course;
import model.Dimension;

/**
 *
 * @author KENSHIN
 */
@WebServlet(name = "Courses", urlPatterns = {"/courses"})
public class Courses extends HttpServlet {

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
            out.println("<title>Servlet Courses</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Courses at " + request.getContextPath() + "</h1>");
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
        //  Default parameters
        int coursePerPage = 6;
        int totalPage;
        //  Get current page index. Set it to 1 if page parameter is null
        int currentPage;
        try {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        } catch (NumberFormatException ex) {
            currentPage = 1;
        }

        CourseDAO courseDAO = new CourseDAO();

        //  Get dimension filter
        int dimensionId;
        try {
            dimensionId = Integer.parseInt(request.getParameter("dimensionId"));
        } catch (NumberFormatException ex) {
            dimensionId = 0;
        }
        //  Get name search
        String courseName = request.getParameter("courseName");
        //  Get courses based on page index
        int startIndex = (currentPage - 1) * coursePerPage;
        List<Course> courses;
        int totalCourse;
        totalCourse = courseDAO.countRecords(dimensionId, courseName);
        courses = courseDAO.getPaginated(startIndex, coursePerPage, dimensionId, courseName);
        //  Transfer courses to course list dto to display
        List<CourseListDto> courseList = new ArrayList<>();
        for (Course c : courses) {
            CourseListDto course = new CourseListDto();
            course.setId(c.getId());
            course.setTitle(c.getTitle());
            course.setThumbnail(c.getThumbnail());
            course.setDimension(c.getDimension());
            course.setDescription(c.getDescription());
            course.setFeatured(c.isFeatured());
            course.setNumberOfParticipants(c.getNumberOfParticipants());
            CourseReviewDAO courseReviewDAO = new CourseReviewDAO();
            double averageRating = courseReviewDAO.getAverageRatingByCourseId(c.getId());
            int ratingCount = courseReviewDAO.getCountOfReviewsByCourseId(c.getId());
            
            course.setCurrentParticipants(courseDAO.numberOfCurrentRegistration(c.getId()));
            course.setNumberOfRating(ratingCount);
            course.setRating(Math.round(averageRating));
            courseReviewDAO.closeConnection();
            courseList.add(course);
        }

        //  Get pagination parameters
        if (totalCourse > coursePerPage) {
            totalPage = totalCourse / coursePerPage + 1;
        } else {
            totalPage = 1;
        }
        //  Get dimensions for filter bar
        DimensionDAO dimensionDAO = new DimensionDAO();
        List<Dimension> dimensions = dimensionDAO.getAll();
        //  Set data to the request
        request.setAttribute("courseName", courseName);
        request.setAttribute("dimensionId", dimensionId);
        
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("courses", courseList);
        request.setAttribute("dimensions", dimensions);
        //  Close connection
        courseDAO.closeConnection();
        dimensionDAO.closeConnection();
        request.getRequestDispatcher("courses.jsp").forward(request, response);
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
