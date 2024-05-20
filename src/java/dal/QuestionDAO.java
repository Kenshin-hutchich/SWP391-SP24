/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Question;

/**
 *
 * @author tudo7
 */
public class QuestionDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    public Question getQuestionById(int id) {
        try {
            String strSelect = "SELECT * FROM Question WHERE Id = ?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt("Id"));
                question.setContent(rs.getString("Content"));
                question.setLevel(rs.getInt("Level"));
                question.setExplaination(rs.getString("Explaination"));
                question.setStatus(rs.getInt("Status"));
                return question;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int importQuestion(String content) {
        int questionId = 0;
        try {
            String strSelect = "INSERT INTO question(Content, Status) VALUES(?,1)";
            stm = connection.prepareStatement(strSelect, stm.RETURN_GENERATED_KEYS);
            stm.setString(1, content);
            stm.executeUpdate();

            rs = stm.getGeneratedKeys();
            if (rs.next()) {
                questionId = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return questionId;
    }

    public List<Question> getQuestionByQuiz(int quizId) {
        List<Question> ltQuestion = new ArrayList<>();
        try {
            String strSelect = "SELECT q1.*, QuizID "
                    + "FROM question q1 "
                    + "LEFT JOIN quizquestion q2 "
                    + "ON q1.Id = q2.QuestionId "
                    + "WHERE QuizId = ?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, quizId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt("Id"));
                question.setContent(rs.getString("Content"));
                question.setLevel(rs.getInt("Level"));
                question.setExplaination(rs.getString("Explaination"));
                question.setStatus(rs.getInt("Status"));
                question.setQuizId(rs.getInt("QuizId"));
                ltQuestion.add(question);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ltQuestion;
    }

    public int numQuestionInQuiz(int quizId) {
        try {
            String strSelect = "SELECT COUNT(*) FROM quizquestion WHERE QuizId = ?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, quizId);
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public int insertQuestion(int quizId, String content) {
        int questionId = 0;
        try {
            String strSelect = "INSERT INTO question(Content, Status) VALUES(?,1)";
            stm = connection.prepareStatement(strSelect, stm.RETURN_GENERATED_KEYS);
            stm.setString(1, content);
            stm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                questionId = rs.getInt(1);
            }

            String strInsert = "INSERT INTO quizquestion(QuizId, QuestionId) VALUES(?, ?)";
            stm = connection.prepareStatement(strInsert);
            stm.setInt(1, quizId);
            stm.setInt(2, questionId);
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return questionId;
    }

    public boolean updateQuestion(int questionId, String newContent) {
        try {
            String strSelect = "UPDATE question SET Content = ? WHERE Id = ?";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, newContent);
            stm.setInt(2, questionId);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteQuestion(int questionId) {
        try {
            String sql = "DELETE FROM quizquestion WHERE QuestionId = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, questionId);
            stm.executeUpdate();

            sql = "DELETE FROM question WHERE Id = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, questionId);
            stm.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public List<Question> getListQuestion() {
        List<Question> ltQuestion = new ArrayList<>();
        try {
            String strSelect = "SELECT * FROM question";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt("Id"));
                question.setContent(rs.getString("Content"));
                question.setLevel(rs.getInt("Level"));
                question.setExplaination(rs.getString("Explaination"));
                question.setStatus(rs.getInt("Status"));
                ltQuestion.add(question);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ltQuestion;
    }
}
