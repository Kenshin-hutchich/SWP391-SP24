/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.common;

import dal.AnswerDAO;
import dal.QuestionDAO;
import dal.QuizDAO;
import dal.ScoreDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Question;

/**
 *
 * @author win
 */
@WebServlet(name = "QuizReviewController", urlPatterns = {"/quiz-review"})
public class QuizReviewController extends HttpServlet {

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
        }
        QuestionDAO qtdao = new QuestionDAO();
        QuizDAO qdao = new QuizDAO();
        ScoreDAO sdao = new ScoreDAO();
        AnswerDAO adao = new AnswerDAO();
        int scoreId = Integer.parseInt(request.getParameter("scoreId"));
        int quizId = sdao.getScoreById(scoreId).getQuizId();
        List<Question> ltQuestion = qtdao.getQuestionByQuiz(quizId);
        String[] selectedAnswers = adao.getSelectedAnswerByScore(scoreId);
        request.setAttribute("ltQuestion", ltQuestion);
        request.setAttribute("quizId", quizId);
        request.setAttribute("quiz", qdao.getQuizById(quizId));
        request.setAttribute("selectedAnswers", selectedAnswers);
        request.setAttribute("score", sdao.getScoreById(scoreId).getScore());
        request.setAttribute("time", sdao.getScoreById(scoreId).getTime());
        request.getRequestDispatcher("quiz-review.jsp").forward(request, response);
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
