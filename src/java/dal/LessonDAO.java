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
import model.Lesson;

/**
 *
 * @author KENSHIN
 */
public class LessonDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    public List<Lesson> getByCourseId(int courseId) {
        List<Lesson> lessons = new ArrayList<>();
        CourseDAO courseDAO = new CourseDAO();
        String strSelect = "SELECT * FROM Lesson WHERE CourseId=? ORDER BY OrderNumber ASC";
        try {
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, courseId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(rs.getInt("Id"));
                lesson.setCourse(courseDAO.getById(rs.getInt("courseId")));
                lesson.setOrderNumber(rs.getInt("OrderNumber"));
                lesson.setTitle(rs.getString("Title"));
                lesson.setVideoSrc(rs.getString("VideoSrc"));
                lesson.setContent(rs.getString("Content"));
                lessons.add(lesson);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            courseDAO.closeConnection();
        }
        return lessons;
    }

    public boolean insertLesson(Lesson lesson) {
        String query = "INSERT INTO Lesson (CourseId, OrderNumber, Title, VideoSrc, Content) VALUES (?, ?, ?, ?, ?)";
        try {
            stm = connection.prepareStatement(query);
            stm.setInt(1, lesson.getCourse().getId());
            stm.setInt(2, lesson.getOrderNumber());
            stm.setString(3, lesson.getTitle());
            stm.setString(4, lesson.getVideoSrc());
            stm.setString(5, lesson.getContent());

            return stm.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public boolean updateLesson(Lesson lesson) {
        String query = "UPDATE Lesson SET CourseId=?, OrderNumber=?, Title=?, VideoSrc=?, Content=? WHERE Id=?";
        try {
            stm = connection.prepareStatement(query);
            stm.setInt(1, lesson.getCourse().getId());
            stm.setInt(2, lesson.getOrderNumber());
            stm.setString(3, lesson.getTitle());
            stm.setString(4, lesson.getVideoSrc());
            stm.setString(5, lesson.getContent());
            stm.setInt(6, lesson.getId());

            return stm.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public boolean deleteLesson(int lessonId) {
        String query = "DELETE FROM Lesson WHERE Id=?";
        try {
            stm = connection.prepareStatement(query);
            stm.setInt(1, lessonId);

            return stm.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
}
