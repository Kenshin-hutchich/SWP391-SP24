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
import model.Answer;
import model.Question;

/**
 *
 * @author tudo7
 */
public class AnswerDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    public Answer getAnswerById(int id) {
        try {
            String strSelect = "SELECT * FROM answer WHERE Id = ?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            Answer answer = null;
            if (rs.next()) {
                answer = new Answer();
                answer.setId(rs.getInt("Id"));
                answer.setContent(rs.getString("Content"));
                answer.setIsCorrect(rs.getInt("IsCorrect"));
                answer.setStatus(rs.getInt("Status"));
                answer.setQuestionId(rs.getInt("QuestionId"));
            }
            return answer;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int importAnswer(int questionId, String content, int isCorrect) {
        int answerId = 0;
        try {
            String strSelect = "INSERT INTO answer(QuestionId, Content, IsCorrect, Status) VALUES(?, ?, ?, 1)";
            stm = connection.prepareStatement(strSelect, stm.RETURN_GENERATED_KEYS);
            stm.setInt(1, questionId);
            stm.setString(2, content);
            stm.setInt(3, isCorrect);

            stm.executeUpdate();

            rs = stm.getGeneratedKeys();
            if (rs.next()) {
                answerId = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return answerId;
    }

    public List<Answer> getAnswerByQuestion(int questionId) {
        List<Answer> ltAnswer = new ArrayList<>();
        try {
            String strSelect = "SELECT * FROM answer WHERE QuestionId = ?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, questionId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Answer answer = new Answer();
                answer.setId(rs.getInt("Id"));
                answer.setContent(rs.getString("Content"));
//                answer.setTitle(rs.getString("Title"));
                answer.setIsCorrect(rs.getInt("IsCorrect"));
                answer.setStatus(rs.getInt("Status"));
                answer.setQuestionId(rs.getInt("QuestionId"));
//                answer.setQuestion(getQuestionById(rs.getInt("QuestionId")));
                ltAnswer.add(answer);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ltAnswer;
    }

    public List<String> getCorrectedAnswersByQuestion(int questionId) {
        List<String> ltAnswer = new ArrayList<>();
        try {
            String strSelect = "SELECT Id FROM answer WHERE QuestionId = ? AND IsCorrect = 1";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, questionId);
            rs = stm.executeQuery();
            while (rs.next()) {
                ltAnswer.add(Integer.toString(rs.getInt(1)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ltAnswer;
    }

    public List<String> getSelectedAnswerByQuestion(String[] selectedAnswers, int questionId) {
        List<String> selectedAnswersForQuestion = new ArrayList<>();
        for (String selectedAnswer : selectedAnswers) {
            if (getAnswerById(Integer.parseInt(selectedAnswer)).getQuestionId() == questionId) {
                selectedAnswersForQuestion.add(selectedAnswer);
            }
        }
        return selectedAnswersForQuestion;
    }

    public float countScore(String[] selectedAnswers, int quizId) {
        float score = 0;
        float scorePerQuestion = 10 / new QuestionDAO().numQuestionInQuiz(quizId);
        List<Question> ltQuestion = new QuestionDAO().getQuestionByQuiz(quizId);
        for (Question question : ltQuestion) {
            List<String> correctAnswer = getCorrectedAnswersByQuestion(question.getId());
            List<String> selectedAnswer = getSelectedAnswerByQuestion(selectedAnswers, question.getId());
            if (correctAnswer.containsAll(selectedAnswer) && selectedAnswer.containsAll(correctAnswer)) {
                score += scorePerQuestion;
            }
        }
        return score;
    }

    public void setSelectedAnswers(String[] selectedAnswers, int scoreId) {
        try {
            for (String selectedAnswer : selectedAnswers) {
                String strSelect = "INSERT INTO answerscore (AnswerId, ScoreId) VALUES (?, ?)";
                stm = connection.prepareStatement(strSelect);
                stm.setInt(1, Integer.parseInt(selectedAnswer));
                stm.setInt(2, scoreId);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean isQuestionCorrect(String[] selectedAnswers, int questionId) {
        List<String> selectedAnswer = getSelectedAnswerByQuestion(selectedAnswers, questionId);
        List<String> correctAnswer = getCorrectedAnswersByQuestion(questionId);
        if (correctAnswer.containsAll(selectedAnswer) && selectedAnswer.containsAll(correctAnswer)) {
            return true;
        }
        return false;
    }

    public String[] getSelectedAnswerByScore(int scoreId) {
        try {
            List<String> ltAnswerRaw = new ArrayList<>();
            String strSelect = "SELECT AnswerId FROM `answerscore` WHERE ScoreId = ?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, scoreId);
            rs = stm.executeQuery();
            while (rs.next()) {
                ltAnswerRaw.add(rs.getString(1));
            }
            String[] ltAnswer = new String[ltAnswerRaw.size()];
            for (int i = 0; i < ltAnswerRaw.size(); i++) {
                ltAnswer[i] = String.valueOf(ltAnswerRaw.get(i));
            }
            return ltAnswer;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int insertAnswer(int questionId) {
        int answerId = 0;
        try {
            String strSelect = "INSERT INTO answer(QuestionId, Content, IsCorrect, Status) VALUES(?, 'Default answer', 0, 1)";
            stm = connection.prepareStatement(strSelect, stm.RETURN_GENERATED_KEYS);
            stm.setInt(1, questionId);
            stm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                answerId = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return answerId;
    }

    public boolean deleteAnswer(int answerId) {
        try {
            String strSelect = "DELETE FROM answerscore WHERE AnswerId = ?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, answerId);
            stm.executeUpdate();
            strSelect = "DELETE FROM answer WHERE Id = ?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, answerId);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean updateAnswer(int answerId, String newContent) {
        try {
            String strSelect = "UPDATE answer SET Content = ? WHERE Id = ?";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, newContent);
            stm.setInt(2, answerId);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean updateAnswer(int answerId, int isCorrect) {
        try {
            String strSelect = "UPDATE answer SET IsCorrect = ? WHERE Id = ?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, isCorrect);
            stm.setInt(2, answerId);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static void main(String[] args) {
        AnswerDAO a = new AnswerDAO();
        boolean i = a.deleteAnswer(22);
    }
}
