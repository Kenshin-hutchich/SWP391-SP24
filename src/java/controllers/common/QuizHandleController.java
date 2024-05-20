/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.common;

import dal.AnswerDAO;
import dal.CourseDAO;
import dal.QuestionDAO;
import dal.QuizDAO;
import dtos.UserSessionDto;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import model.Course;
import model.Question;
import model.Quiz;

/**
 *
 * @author win
 */
@WebServlet(name = "QuizHandleController", urlPatterns = {"/quiz-handle"})
public class QuizHandleController extends HttpServlet {

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
        if (request.getSession().getAttribute("userSessionDto") == null) {
            response.sendRedirect("login");
        } else {
            QuizDAO qdao = new QuizDAO();
            QuestionDAO qtdao = new QuestionDAO();

            String quizIdRaw = request.getParameter("quizId");
            int quizId = Integer.parseInt(quizIdRaw);
            Quiz quiz = qdao.getQuizById(quizId);
            List<Question> ltQuestion = qtdao.getQuestionByQuiz(quizId);
            Collections.shuffle(ltQuestion);
            CourseDAO courseDAO = new CourseDAO();
            Course course = courseDAO.getById(quiz.getCourseId());
            request.setAttribute("ltQuestion", ltQuestion);
            request.setAttribute("quizId", quizId);
            request.setAttribute("quiz", quiz);
            request.setAttribute("course", course);

            courseDAO.closeConnection();
            qdao.closeConnection();
            qtdao.closeConnection();
            request.getRequestDispatcher("quiz-handle-1.jsp").forward(request, response);
        }
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
        QuizDAO qdao = new QuizDAO();
        AnswerDAO adao = new AnswerDAO();
        float score = 0;
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        int time = Integer.parseInt(request.getParameter("time"));
        String[] selectedAnswers = request.getParameterValues("answers");
        if (selectedAnswers != null) {
            score = adao.countScore(selectedAnswers, quizId);
        }

        UserSessionDto userSessionDto = (UserSessionDto) request.getSession().getAttribute("userSessionDto");
        Integer userId = userSessionDto.getId();

        int scoreId = qdao.setScore(
                quizId,
                userId,
                score,
                qdao.getQuizById(quizId).getTime() - time);
        if (selectedAnswers != null) {
            adao.setSelectedAnswers(selectedAnswers, scoreId);
        }
        response.sendRedirect("quiz-review?scoreId=" + scoreId);
        System.out.println(time);
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
