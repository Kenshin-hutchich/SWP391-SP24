package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Quiz;

/**
 *
 * @author tudo7
 */
public class QuizDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    public Quiz getQuizById(int id) {
        try {
            String strSelect = "SELECT * FROM Quiz WHERE Id = ?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setId(rs.getInt("Id"));
                quiz.setTitle(rs.getString("Title"));
                quiz.setContent(rs.getString("Content"));
                quiz.setStatus(rs.getInt("Status"));
                quiz.setCourseId(rs.getInt("CourseId"));
                quiz.setTime(rs.getInt("Time"));
                CourseDAO courseDAO = new CourseDAO();
                quiz.setCourse(courseDAO.getById(rs.getInt("CourseId")));
                courseDAO.closeConnection();
                return quiz;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Quiz> getQuizzesByCourseId(int courseId) {
        List<Quiz> quizList = new ArrayList<>();
        try {
            String strSelect = "SELECT * FROM Quiz WHERE CourseId = ?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, courseId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setId(rs.getInt("Id"));
                quiz.setTitle(rs.getString("Title"));
                quiz.setContent(rs.getString("Content"));
                quiz.setStatus(rs.getInt("Status"));
                quiz.setCourseId(rs.getInt("CourseId"));
                quiz.setTime(rs.getInt("Time"));
                CourseDAO courseDAO = new CourseDAO();
                quiz.setCourse(courseDAO.getById(rs.getInt("CourseId")));
                courseDAO.closeConnection();
                quizList.add(quiz);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return quizList;
    }

    /**
     * Save quiz results into database
     *
     * @param quizId
     * @param userId
     * @param score
     * @return
     */
    public int setScore(int quizId, int userId, double score, int time) {
        int generatedId = -1;
        try {
            DateFormat fmt = new SimpleDateFormat("HH:mm");
            String strSelect = "INSERT INTO quizscore (QuizId, Score, UserId, Time) VALUES (?, ?, ?, ?)";
            stm = connection.prepareStatement(strSelect, stm.RETURN_GENERATED_KEYS);
            stm.setInt(1, quizId);
            stm.setDouble(2, score);
            stm.setInt(3, userId);
            stm.setInt(4, time);
            stm.executeUpdate();
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return generatedId;
    }

//    /**
//     * Get list of subjects
//     *
//     * @return
//     */
//    public List<String> getSubjectList() {
//        List<String> ltSubject = new ArrayList<>();
//        try {
//            String strSelect = "SELECT DISTINCT q.SubjectCode FROM quiz q";
//            stm = connection.prepareStatement(strSelect);
//            rs = stm.executeQuery();
//            while (rs.next()) {
//                ltSubject.add(rs.getString(1));
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return ltSubject;
//    }
    public void updateQuiz(int quizId, String title, String desc, int courseId, int time, int status) {
        try {
            String strSelect = "UPDATE quiz SET Title = ?, Content = ?, Status = ?, CourseId = ?, Time = ? WHERE Id = ?";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, title);
            stm.setString(2, desc);
            stm.setInt(3, status);
            stm.setInt(4, courseId);
            stm.setInt(5, time);
            stm.setInt(6, quizId);
            stm.executeUpdate();
            closeConnection();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
