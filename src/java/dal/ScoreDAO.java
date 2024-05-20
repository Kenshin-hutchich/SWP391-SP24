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
import model.Quiz;
import model.Score;

/**
 *
 * @author win
 */
public class ScoreDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    /**
     * Get list of taken quiz for quiz history
     *
     * @param userId
     * @param subject
     * @return
     */
    public List<Score> getListScore(int userId, int courseId) {
        List<Score> ltScore = new ArrayList<>();
        try {
            String strSelect = "SELECT qc.* "
                    + "FROM quizscore qc "
                    + "LEFT JOIN quiz q "
                    + "ON qc.QuizId = q.Id "
                    + "WHERE qc.UserId = ? "
                    + "AND q.CourseId = ?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, userId);
            stm.setInt(2, courseId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Score score = new Score();
                score.setScoreId(rs.getInt("Id"));
                score.setQuiz(new QuizDAO().getQuizById(rs.getInt("QuizId")));
                score.setScore(rs.getDouble("Score"));
                score.setUserId(rs.getInt("UserId"));
                score.setTime(rs.getInt("Time"));
                ltScore.add(score);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ltScore;
    }

    public Score getScoreById(int id) {
        try {
            String strSelect = "SELECT * FROM `quizscore` WHERE Id = ?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Score score = new Score();
                score.setScoreId(rs.getInt("Id"));
                score.setQuizId(rs.getInt("QuizId"));
                score.setScore(rs.getDouble("Score"));
                score.setUserId(rs.getInt("UserId"));
                score.setTime(rs.getInt("Time"));
                return score;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
