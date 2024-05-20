/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.common;

import dal.CourseDAO;
import dal.LessonDAO;
import dal.QuizDAO;
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
import java.util.List;
import model.Course;
import model.Quiz;

/**
 *
 * @author KENSHIN
 */
@WebServlet(name = "Lesson", urlPatterns = {"/lesson"})
public class Lesson extends HttpServlet {

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
            out.println("<title>Servlet Lesson</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Lesson at " + request.getContextPath() + "</h1>");
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
        //  Get course information
        int courseId;
        Course course;
        //  If course ID is illegal (not a number or doesn't exist in database), return 404 Not Found Error
        try {
            courseId = Integer.parseInt(request.getParameter("courseId"));
            CourseDAO courseDAO = new CourseDAO();
            course = courseDAO.getById(courseId);
            courseDAO.closeConnection();
            if (course.getId() == 0) {
                throw new IllegalStateException("Course doesn't exist.");
            }
        } catch (NumberFormatException | IllegalStateException ex) {
            System.out.println(ex.getMessage());
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
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
        //  Temporary disable this section as all courses will be free to learn.
        /*
        //  Get user registration
        if (!courseDAO.isRegistered(userSessionDto.getId(), courseId)) {
            courseDAO.closeConnection();
            //  If user didn't register this course, return 403 Forbidden Error
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        */
        
        //  At this line, courseDAO connection haven't been closed yet

        //  Get requested lesson content
        LessonDAO lessonDAO = new LessonDAO();
        List<model.Lesson> lessons = lessonDAO.getByCourseId(courseId);
        model.Lesson lesson = new model.Lesson();
        int lessonId;
        try {
            lessonId = Integer.parseInt(request.getParameter("lessonId"));
            for (model.Lesson l : lessons) {
                if (l.getId() == lessonId) {
                    lesson = l;
                }
            }
            if (lesson.getId() == 0) {
                throw new IllegalStateException("Requested lesson doesn't exist.");
            }
        } catch (IllegalStateException ex) {
            //  If lesson ID doesn't exist in database), return 404 Not Found Error
            lessonDAO.closeConnection();
            courseDAO.closeConnection();
            System.out.println(ex.getMessage());
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } catch (NumberFormatException ex) {
            //  If there's no value for lesson ID, use the first lesson of the course (if any).
            lesson = !lessons.isEmpty() ? lessons.get(0) : new model.Lesson();
        }
        //  At this line, lessonDAO and courseDAO connections haven't been closed yet.
        
        //  Get quizzes of this course
        QuizDAO quizDAO = new QuizDAO();
        List<Quiz> quizzes = quizDAO.getQuizzesByCourseId(courseId);

        //  Set data to the request
        request.setAttribute("course", course);
        request.setAttribute("lesson", lesson);
        request.setAttribute("lessons", lessons);
        request.setAttribute("quizzes", quizzes);

        //  Close remaining connections
        lessonDAO.closeConnection();
        courseDAO.closeConnection();
        quizDAO.closeConnection();

        request.getRequestDispatcher("lesson.jsp").forward(request, response);
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
