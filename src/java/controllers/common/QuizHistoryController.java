/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.common;

import dal.CourseDAO;
import dal.QuizDAO;
import dal.ScoreDAO;
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
import model.Score;

/**
 *
 * @author win
 */
@WebServlet(name = "QuizHistoryController", urlPatterns = {"/quiz-history"})
public class QuizHistoryController extends HttpServlet {

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

        ScoreDAO sdao = new ScoreDAO();
        String subject = request.getParameter("scode");
        int courseId = 1;
        // FIX CUNG USERID = 2
//        int userId = Integer.parseInt(request.getSession().getAttribute("UID").toString());
        List<Course> ltCourse = new CourseDAO().getAll();
        if (subject == null) {
            courseId = ltCourse.get(0).getId();
        }
        List<Score> ltScore = sdao.getListScore(userSessionDto.getId(), courseId);
        request.setAttribute("ltSubject", ltCourse);
        request.setAttribute("ltScore", ltScore);
        sdao.closeConnection();
        request.getRequestDispatcher("quiz-history.jsp").forward(request, response);
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
