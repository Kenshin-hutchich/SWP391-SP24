/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.admin;

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
@WebServlet(name = "CourseManage", urlPatterns = {"/admin-course-manage"})
public class CourseManage extends HttpServlet {

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
            out.println("<title>Servlet CourseManage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CourseManage at " + request.getContextPath() + "</h1>");
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
        CourseDAO courseDAO = new CourseDAO();

        List<Course> pendingCourses = courseDAO.getWaitingCourse();

        List<Integer> topCourseIds = courseDAO.getTopCourseId(3);
        List<CourseListDto> topCourses = new ArrayList<>();
        for (int i : topCourseIds) {
            Course c = courseDAO.getById(i);
            CourseListDto course = toCourseListDto(c);
            topCourses.add(course);
        }

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

        List<Course> courses;
        int totalCourse;
        totalCourse = courseDAO.countRecords(dimensionId, courseName);
        if ((currentPage - 1) * coursePerPage > totalCourse) {
            currentPage = 1;
        }
        int startIndex = (currentPage - 1) * coursePerPage;
        courses = courseDAO.getPaginated(startIndex, coursePerPage, dimensionId, courseName);
        //  Transfer courses to course list dto to display
        List<CourseListDto> courseList = new ArrayList<>();
        for (Course c : courses) {
            CourseListDto course = toCourseListDto(c);
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

        request.setAttribute("pendingCourses", pendingCourses);

        request.setAttribute("pendingCourses", pendingCourses);
        request.setAttribute("topCourses", topCourses);

        //  Close connection
        dimensionDAO.closeConnection();
        courseDAO.closeConnection();
        request.getRequestDispatcher("admin/courses.jsp").forward(request, response);
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
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        boolean status = Boolean.parseBoolean(request.getParameter("isApproved"));
        CourseDAO courseDAO = new CourseDAO();
        Course course = courseDAO.getById(courseId);
        course.setStatus(status);
        courseDAO.approve(courseId);
        response.sendRedirect("admin-course-manage");
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
