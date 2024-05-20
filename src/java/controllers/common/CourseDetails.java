/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.common;

import dal.CourseDAO;
import dal.CourseReviewDAO;
import dal.LessonDAO;
import dal.QuizDAO;
import dal.UserDAO;
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
import model.Lesson;
import model.Quiz;

/**
 *
 * @author KENSHIN
 */
@WebServlet(name = "Course Details", urlPatterns = {"/course-details"})
public class CourseDetails extends HttpServlet {

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
            out.println("<title>Servlet LearnCourse</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LearnCourse at " + request.getContextPath() + "</h1>");
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
        int courseId;
        try {
            courseId = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException ex) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //  Get user information
        UserSessionDto user = new UserSessionDto();
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession(false);
        if (cookies != null) {
            String sessionID;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    sessionID = cookie.getValue();
                    if (session != null && sessionID.equals(session.getId())) {
                        if ((UserSessionDto) session.getAttribute("userSessionDto") != null) {
                            user = (UserSessionDto) session.getAttribute("userSessionDto");
                        }
                    }
                }
            }
        }
        
        
        CourseDAO courseDAO = new CourseDAO();
        Course course = courseDAO.getById(courseId);
        Boolean hasAccess = courseDAO.isRegistered(user.getId(), courseId);
        LessonDAO lessonDAO = new LessonDAO();
        List<Lesson> lessons = lessonDAO.getByCourseId(courseId);
        QuizDAO quizDAO = new QuizDAO();
        List<Quiz> quizzes = quizDAO.getQuizzesByCourseId(courseId);
        UserDAO userDAO = new UserDAO();
        int studentNumber = courseDAO.numberOfCurrentRegistration(courseId);

        CourseReviewDAO reviewDAO = new CourseReviewDAO();
        int totalReview = reviewDAO.getCountOfReviewsByCourseId(courseId);
        double averageRating = reviewDAO.getAverageRatingByCourseId(courseId);
        averageRating = (double) Math.round(averageRating * 10) / 10.0;
        List<Integer> numberOfReviews = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            numberOfReviews.add(reviewDAO.getCountOfReviewsByRating(courseId, i));
        }

        reviewDAO.closeConnection();
        courseDAO.closeConnection();
        lessonDAO.closeConnection();
        quizDAO.closeConnection();
        userDAO.closeConnection();

        request.setAttribute("hasAccess", hasAccess);
        request.setAttribute("totalReview", totalReview);
        request.setAttribute("roundedRating", Math.round(averageRating));
        request.setAttribute("averageRating", averageRating);
        request.setAttribute("numberOfReviews", numberOfReviews);
        request.setAttribute("course", course);
        request.setAttribute("lessons", lessons);
        request.setAttribute("quizzes", quizzes);
        request.setAttribute("studentNumber", studentNumber);
        request.getRequestDispatcher("course-details.jsp").forward(request, response);
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
