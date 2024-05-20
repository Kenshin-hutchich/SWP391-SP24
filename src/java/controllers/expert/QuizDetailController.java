/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.expert;

import dal.AnswerDAO;
import dal.CourseDAO;
import dal.QuestionDAO;
import dal.QuizDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Question;
import model.Quiz;
import ultility.ConvertTime;

/**
 *
 * @author win
 */
@WebServlet(name = "QuizDetailController", urlPatterns = {"/quiz-detail"})
public class QuizDetailController extends HttpServlet {

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
            request.setAttribute("ltQuestion", ltQuestion);
            request.setAttribute("quizId", quizId);
            request.setAttribute("quiz", qdao.getQuizById(quizId));
            request.setAttribute("time", ConvertTime.convertSecondsToHHMMSS(quiz.getTime()));
            request.setAttribute("ltCourse", new CourseDAO().getAll());
            request.getRequestDispatcher("expert/quiz-detail-1.jsp").forward(request, response);
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
        AnswerDAO adao = new AnswerDAO();
        QuestionDAO qtdao = new QuestionDAO();
        QuizDAO qdao = new QuizDAO();
        String action = request.getParameter("action");
        switch (action) {
            case "answerContent":
                String idRaw = request.getParameter("id");
                String content = request.getParameter("content");
                boolean temp = adao.updateAnswer(Integer.parseInt(idRaw), content);
                adao.closeConnection();
                qtdao.closeConnection();
                qdao.closeConnection();
                break;
            case "isCorrect":
                idRaw = request.getParameter("id");
                String isCorrect = request.getParameter("isCorrect");
                temp = adao.updateAnswer(
                        Integer.parseInt(idRaw),
                        Integer.parseInt(isCorrect));
                adao.closeConnection();
                qtdao.closeConnection();
                qdao.closeConnection();
                break;
            case "deleteAnswer":
                idRaw = request.getParameter("id");
                temp = adao.deleteAnswer(Integer.parseInt(idRaw));
                adao.closeConnection();
                qtdao.closeConnection();
                qdao.closeConnection();
                break;
            case "insertAnswer":
                idRaw = request.getParameter("id");
                // insert new ans
                int ansId = adao.insertAnswer(Integer.parseInt(idRaw));
                adao.closeConnection();
                qtdao.closeConnection();
                qdao.closeConnection();
                break;
            case "insertQuestion":
                idRaw = request.getParameter("id");
                int quesId = qtdao.insertQuestion(Integer.parseInt(idRaw), "Default Question");
                adao.closeConnection();
                qtdao.closeConnection();
                qdao.closeConnection();
                break;
            case "deleteQuestion":
                idRaw = request.getParameter("id");
                temp = qtdao.deleteQuestion(Integer.parseInt(idRaw));
                adao.closeConnection();
                qtdao.closeConnection();
                qdao.closeConnection();
                break;
            case "updateQuestion":
                idRaw = request.getParameter("id");
                content = request.getParameter("content");
                temp = qtdao.updateQuestion(Integer.parseInt(idRaw), content);
                adao.closeConnection();
                qtdao.closeConnection();
                qdao.closeConnection();
                break;
            case "updateInformation":
                idRaw = request.getParameter("quizId");
                String timeRaw = request.getParameter("time");
                String statusRaw = request.getParameter("status");
                qdao.updateQuiz(
                        Integer.parseInt(idRaw),
                        request.getParameter("title"),
                        request.getParameter("description"),
                        Integer.parseInt(request.getParameter("courseId")),
                        ConvertTime.convertTimeToSeconds(timeRaw),
                        Integer.parseInt(statusRaw));
                request.getSession().setAttribute("isUpdateSuccessfully", true);
                response.sendRedirect("quiz-detail?quizId=" + idRaw);
                adao.closeConnection();
                qtdao.closeConnection();
                qdao.closeConnection();
                break;
            default:
                adao.closeConnection();
                qtdao.closeConnection();
                qdao.closeConnection();
                throw new AssertionError();
        }

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
