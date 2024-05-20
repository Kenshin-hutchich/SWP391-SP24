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
import model.CourseReview;

/**
 *
 * @author KENSHIN
 */
public class CourseReviewDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    public List<CourseReview> getByCourseId(int courseId) {
        UserDAO userDAO = new UserDAO();
        CourseDAO courseDAO = new CourseDAO();
        List<CourseReview> courseReviews = new ArrayList<>();
        String strSelect = "SELECT * FROM CourseReview WHERE CourseId=?";
        try {
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, courseId);
            rs = stm.executeQuery();
            while (rs.next()) {
                CourseReview courseReview = new CourseReview();
                courseReview.setUser(userDAO.getById(rs.getInt("UserId")));
                courseReview.setCourse(courseDAO.getById(rs.getInt("CourseId")));
                courseReview.setRating(rs.getInt("Rating"));
                courseReviews.add(courseReview);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            userDAO.closeConnection();
            courseDAO.closeConnection();
        }
        return courseReviews;
    }

    public double getAverageRatingByCourseId(int courseId) {
        double averageRating = 0;
        String strSelect = "SELECT AVG(Rating) AS averageRating FROM CourseReview WHERE CourseId=?";
        try {
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, courseId);
            rs = stm.executeQuery();
            if (rs.next()) {
                averageRating = rs.getDouble("averageRating");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return averageRating;
    }

    public int getCountOfReviewsByCourseId(int courseId) {
        int count = 0;
        String strSelect = "SELECT COUNT(*) AS count FROM CourseReview WHERE CourseId=?";
        try {
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, courseId);
            rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return count;
    }

    public int getCountOfReviewsByRating(int courseId, int rating) {
        int count = 0;
        String strSelect = "SELECT COUNT(*) AS count FROM CourseReview WHERE CourseId=? AND Rating=?";
        try {
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, courseId);
            stm.setInt(2, rating);
            rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return count;
    }

    public double calculateAverageRating() {
        CourseDAO courseDAO = new CourseDAO();
        double averageRating = 0.0;
        int totalRatings = 0;
        String strSelect = "SELECT Rating FROM CourseReview";
        try {
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                averageRating += rs.getInt("Rating");
                totalRatings++;
            }
            if (totalRatings > 0) {
                averageRating /= totalRatings;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            courseDAO.closeConnection();
        }
        return averageRating;
    }

    public List<CourseReview> getByUserId(int userId) {
        UserDAO userDAO = new UserDAO();
        CourseDAO courseDAO = new CourseDAO();
        List<CourseReview> courseReviews = new ArrayList<>();
        String strSelect = "SELECT * FROM CourseReview WHERE UserId=?";
        try {
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, userId);
            rs = stm.executeQuery();
            while (rs.next()) {
                CourseReview courseReview = new CourseReview();
                courseReview.setUser(userDAO.getById(rs.getInt("UserId")));
                courseReview.setCourse(courseDAO.getById(rs.getInt("CourseId")));
                courseReview.setRating(rs.getInt("Rating"));
                courseReviews.add(courseReview);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            userDAO.closeConnection();
            courseDAO.closeConnection();
        }
        return courseReviews;
    }

    public int total() {
        int count = 0;
        String query = "SELECT COUNT(*) AS TotalReviews FROM CourseReview";
        try {
            stm = connection.prepareStatement(query);
            rs = stm.executeQuery(query);
            if (rs.next()) {
                count = rs.getInt("TotalReviews");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

}
