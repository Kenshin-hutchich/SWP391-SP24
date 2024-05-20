/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.expert;

import dal.CourseDAO;
import dal.CourseReviewDAO;
import dtos.CourseListDto;
import dtos.UserSessionDto;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Course;

/**
 *
 * @author KENSHIN
 */
@WebServlet(name = "MyCourses", urlPatterns = {"/my-courses"})
public class MyCourses extends HttpServlet {

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
        //  Get user information from current session
        UserSessionDto userSessionDto = new UserSessionDto();
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession(false);
        try {
            //  This line will throw NullPointerException if cookies is null
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    String sessionID = cookie.getValue();
                    //  This line will throw NullPointerException if current session is null
                    if (sessionID.equals(session.getId())) {
                        //  Throw a NullPointerException if session doesn't store user information
                        userSessionDto = (UserSessionDto) session.getAttribute("userSessionDto");
                        if (userSessionDto == null) {
                            throw new NullPointerException();
                        }
                    }
                }
            }
        } catch (NullPointerException ex) {
            //  If any of those cases happen, it means user haven't login yet. Return 403 Forbidden Error
            System.out.println(ex.getMessage());
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        CourseDAO courseDAO = new CourseDAO();
        List<CourseListDto> courses = new ArrayList<>();
        for (Course c : courseDAO.getCourseByOwnerId(userSessionDto.getId())) {
            CourseListDto course = new CourseListDto();
            course.setId(c.getId());
            course.setCode(c.getSubjectCode());
            course.setTitle(c.getTitle());
            course.setThumbnail(c.getThumbnail());
            course.setDimension(c.getDimension());
            course.setDescription(c.getDescription());
            course.setStatus(c.getStatus());
            course.setFeatured(c.isFeatured());
            course.setNumberOfParticipants(c.getNumberOfParticipants());
            CourseReviewDAO courseReviewDAO = new CourseReviewDAO();
            double averageRating = courseReviewDAO.getAverageRatingByCourseId(c.getId());
            averageRating = (double) Math.round(averageRating * 10) / 10.0;
            int ratingCount = courseReviewDAO.getCountOfReviewsByCourseId(c.getId());
            
            course.setCurrentParticipants(courseDAO.numberOfCurrentRegistration(c.getId()));
            course.setNumberOfRating(ratingCount);
            course.setRating(averageRating);
            courseReviewDAO.closeConnection();
            courses.add(course);
        }
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("expert/courses.jsp").forward(request, response);
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
