/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.Course;

/**
 *
 * @author KENSHIN
 */
public class CourseDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    public List<Integer> getTopCourseId(int numberOfCourse) {
        List<Integer> courseIds = new ArrayList<>();
        String strSelect = "SELECT \n"
                + "    CourseId,\n"
                + "    ROUND(\n"
                + "        (\n"
                + "            (p + 1.96 * 1.96 / (2 * n)) \n"
                + "            / \n"
                + "            (1 + 1.96 * 1.96 / n)\n"
                + "        ), 2\n"
                + "    ) AS WilsonScoreRating\n"
                + "FROM (\n"
                + "    SELECT \n"
                + "        CourseId,\n"
                + "        COUNT(*) AS n,\n"
                + "        SUM(Rating) AS sum_rating,\n"
                + "        SUM(Rating) / COUNT(*) AS p\n"
                + "    FROM CourseReview\n"
                + "    GROUP BY CourseId\n"
                + ") AS subquery\n"
                + "ORDER BY WilsonScoreRating DESC";
        try {
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next() && numberOfCourse > 0) {
                courseIds.add(rs.getInt(1));
                numberOfCourse--;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return courseIds;
    }

    public List<Course> getCourseByDimensionId(int dimensionId) {
        List<Course> courses = new ArrayList<>();
        UserDAO userDAO = new UserDAO();
        DimensionDAO dimensionDAO = new DimensionDAO();
        String strSelect = "SELECT * FROM Course WHERE DimensionId=?";
        try {
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, dimensionId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("Id"));
                course.setSubjectCode(rs.getString("SubjectCode"));
                course.setTitle(rs.getString("Title"));
                course.setDescription(rs.getString("Description"));
                course.setThumbnail(rs.getString("Thumbnail"));
                course.setNumberOfParticipants(rs.getInt("NumberOfParticipants"));
                course.setDimension(dimensionDAO.getById(rs.getInt("DimensionId")));
                course.setOwner(userDAO.getById(rs.getInt("OwnerId")));
                course.setStatus(rs.getBoolean("Status"));
                course.setFeatured(rs.getBoolean("Featured"));
                courses.add(course);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            userDAO.closeConnection();
            dimensionDAO.closeConnection();
        }
        return courses;
    }
    
    public List<Course> getCourseByOwnerId(int ownerId) {
        List<Course> courses = new ArrayList<>();
        UserDAO userDAO = new UserDAO();
        DimensionDAO dimensionDAO = new DimensionDAO();
        String strSelect = "SELECT * FROM Course WHERE OwnerId=?";
        try {
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, ownerId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("Id"));
                course.setSubjectCode(rs.getString("SubjectCode"));
                course.setTitle(rs.getString("Title"));
                course.setDescription(rs.getString("Description"));
                course.setThumbnail(rs.getString("Thumbnail"));
                course.setNumberOfParticipants(rs.getInt("NumberOfParticipants"));
                course.setDimension(dimensionDAO.getById(rs.getInt("DimensionId")));
                course.setOwner(userDAO.getById(rs.getInt("OwnerId")));
                course.setStatus(rs.getBoolean("Status"));
                course.setFeatured(rs.getBoolean("Featured"));
                courses.add(course);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            userDAO.closeConnection();
            dimensionDAO.closeConnection();
        }
        return courses;
    }

    public boolean approve(int courseId) {
        String strUpdate = "UPDATE Course SET Status = ? WHERE Id = ?";
        try {
            stm = connection.prepareStatement(strUpdate);
            // Set giá trị cho trường Status là true (được phê duyệt)
            stm.setBoolean(1, true);
            stm.setInt(2, courseId);
            // Thực thi câu lệnh update
            int rowsUpdated = stm.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            System.out.println("An error occurred: " + ex.getMessage());
        }
        return false;
    }

    public Course getById(int courseId) {
        UserDAO userDAO = new UserDAO();
        DimensionDAO dimensionDAO = new DimensionDAO();
        Course course = new Course();
        String strSelect = "SELECT * FROM Course WHERE Id=?";
        try {
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, courseId);
            rs = stm.executeQuery();
            if (rs.next()) {
                course.setId(rs.getInt("Id"));
                course.setSubjectCode(rs.getString("SubjectCode"));
                course.setTitle(rs.getString("Title"));
                course.setDescription(rs.getString("Description"));
                course.setThumbnail(rs.getString("Thumbnail"));
                course.setNumberOfParticipants(rs.getInt("NumberOfParticipants"));
                course.setDimension(dimensionDAO.getById(rs.getInt("DimensionId")));
                course.setOwner(userDAO.getById(rs.getInt("OwnerId")));
                course.setStatus(rs.getBoolean("Status"));
                course.setFeatured(rs.getBoolean("Featured"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            userDAO.closeConnection();
            dimensionDAO.closeConnection();
        }
        return course;
    }

    public int getTotalByDimensionId(int dimensionId) {
        int total = 0;
        String strSelect = "SELECT COUNT(*) AS total FROM Course WHERE DimensionId=?";
        try {
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, dimensionId);
            rs = stm.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return total;
    }

    public int totalNumberOfParticipants() {
        int totalParticipants = 0;
        try {
            String query = "SELECT SUM(NumberOfParticipants) AS TotalParticipants FROM Course";
            stm = connection.prepareStatement(query);
            rs = stm.executeQuery();
            if (rs.next()) {
                totalParticipants = rs.getInt("TotalParticipants");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return totalParticipants;
    }

    public List<Course> getAll() {
        List<Course> courseList = new ArrayList<>();
        UserDAO userDAO = new UserDAO();
        DimensionDAO dimensionDAO = new DimensionDAO();
        String strSelect = "SELECT * FROM Course";
        try {
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("Id"));
                course.setSubjectCode(rs.getString("SubjectCode"));
                course.setTitle(rs.getString("Title"));
                course.setDescription(rs.getString("Description"));
                course.setThumbnail(rs.getString("Thumbnail"));
                course.setNumberOfParticipants(rs.getInt("NumberOfParticipants"));
                course.setDimension(dimensionDAO.getById(rs.getInt("DimensionId")));
                course.setOwner(userDAO.getById(rs.getInt("OwnerId")));
                course.setStatus(rs.getBoolean("Status"));
                course.setFeatured(rs.getBoolean("Featured"));
                courseList.add(course);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            userDAO.closeConnection();
            dimensionDAO.closeConnection();
        }
        return courseList;
    }

    public List<Course> getWaitingCourse() {
        List<Course> courseList = new ArrayList<>();
        UserDAO userDAO = new UserDAO();
        DimensionDAO dimensionDAO = new DimensionDAO();
        String strSelect = "SELECT * FROM Course WHERE Status IS NULL";
        try {
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("Id"));
                course.setSubjectCode(rs.getString("SubjectCode"));
                course.setTitle(rs.getString("Title"));
                course.setDescription(rs.getString("Description"));
                course.setThumbnail(rs.getString("Thumbnail"));
                course.setNumberOfParticipants(rs.getInt("NumberOfParticipants"));
                course.setDimension(dimensionDAO.getById(rs.getInt("DimensionId")));
                course.setOwner(userDAO.getById(rs.getInt("OwnerId")));
                course.setStatus(rs.getBoolean("Status"));
                course.setFeatured(rs.getBoolean("Featured"));
                courseList.add(course);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            userDAO.closeConnection();
            dimensionDAO.closeConnection();
        }
        return courseList;
    }

    public int getWaitingCourseNumber() {
        int courseNumber = 0;
        String strSelect = "SELECT COUNT(*) FROM Course WHERE Status IS NULL";
        try {
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            if (rs.next()) {
                courseNumber = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return courseNumber;
    }

    public int getTotal() {
        int count = 0;
        String countQuery = "SELECT COUNT(*) AS total FROM Course";
        try {
            stm = connection.prepareStatement(countQuery);
            rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return count;
    }

    public int countRecords(int dimensionId, String courseName) {
        int count = 0;
        String strSelect;
        try {
            if (dimensionId != 0 && courseName != null && !courseName.isEmpty()) {
                strSelect = "SELECT COUNT(*) AS count FROM Course WHERE Status=1 AND DimensionId=? AND Title LIKE ?";
                stm = connection.prepareStatement(strSelect);
                stm.setInt(1, dimensionId);
                stm.setString(2, "%" + courseName + "%");
            } else if (dimensionId != 0) {
                strSelect = "SELECT COUNT(*) AS count FROM Course WHERE Status=1 AND DimensionId=?";
                stm = connection.prepareStatement(strSelect);
                stm.setInt(1, dimensionId);
            } else if (courseName != null && !courseName.isEmpty()) {
                strSelect = "SELECT COUNT(*) AS count FROM Course WHERE Status=1 AND Title LIKE ?";
                stm = connection.prepareStatement(strSelect);
                stm.setString(1, "%" + courseName + "%");
            } else {
                strSelect = "SELECT COUNT(*) AS count FROM Course WHERE Status=1";
                stm = connection.prepareStatement(strSelect);
            }

            rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return count;
    }

    public List<Course> getPaginated(int startIndex, int numOfRecord, int dimensionId, String courseName) {
        List<Course> courseList = new ArrayList<>();
        UserDAO userDAO = new UserDAO();
        DimensionDAO dimensionDAO = new DimensionDAO();
        String strSelect;
        try {
            if (dimensionId != 0 && courseName != null && !courseName.isEmpty()) {
                strSelect = "SELECT * FROM Course WHERE Status=1 AND DimensionId=? AND Title LIKE ? LIMIT ?, ?";
                stm = connection.prepareStatement(strSelect);
                stm.setInt(1, dimensionId);
                stm.setString(2, "%" + courseName + "%");
                stm.setInt(3, startIndex);
                stm.setInt(4, numOfRecord);
            } else if (dimensionId != 0) {
                strSelect = "SELECT * FROM Course WHERE Status=1 AND DimensionId=? LIMIT ?, ?";
                stm = connection.prepareStatement(strSelect);
                stm.setInt(1, dimensionId);
                stm.setInt(2, startIndex);
                stm.setInt(3, numOfRecord);
            } else if (courseName != null && !courseName.isEmpty()) {
                strSelect = "SELECT * FROM Course WHERE Status=1 AND Title LIKE ? LIMIT ?, ?";
                stm = connection.prepareStatement(strSelect);
                stm.setString(1, "%" + courseName + "%");
                stm.setInt(2, startIndex);
                stm.setInt(3, numOfRecord);
            } else {
                strSelect = "SELECT * FROM Course WHERE Status=1 LIMIT ?, ?";
                stm = connection.prepareStatement(strSelect);
                stm.setInt(1, startIndex);
                stm.setInt(2, numOfRecord);
            }

            rs = stm.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("Id"));
                course.setSubjectCode(rs.getString("SubjectCode"));
                course.setTitle(rs.getString("Title"));
                course.setDescription(rs.getString("Description"));
                course.setThumbnail(rs.getString("Thumbnail"));
                course.setNumberOfParticipants(rs.getInt("NumberOfParticipants"));
                course.setDimension(dimensionDAO.getById(rs.getInt("DimensionId")));
                course.setOwner(userDAO.getById(rs.getInt("OwnerId")));
                course.setStatus(rs.getBoolean("Status"));
                course.setFeatured(rs.getBoolean("Featured"));
                courseList.add(course);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            userDAO.closeConnection();
            dimensionDAO.closeConnection();
        }
        return courseList;
    }

    public boolean isRegistered(int userId, int courseId) {
        String query = "SELECT * FROM CourseRegistration WHERE UserId=? AND CourseId=? AND Status IS NOT NULL";
        try {
            stm = connection.prepareStatement(query);
            stm.setInt(1, userId);
            stm.setInt(2, courseId);

            rs = stm.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public int numberOfCurrentRegistration(int courseId) {
        String query = "SELECT COUNT(*) FROM CourseRegistration WHERE CourseId=? AND Status IS FALSE";
        try {
            stm = connection.prepareStatement(query);
            stm.setInt(1, courseId);

            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public int totalNumberOfRegistration() {
        String query = "SELECT COUNT(*) FROM CourseRegistration";
        try {
            stm = connection.prepareStatement(query);

            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public int totalNumberOfRegistrationOfCourse(int courseId) {
        String query = "SELECT COUNT(*) FROM CourseRegistration WHERE CourseId=?";
        try {
            stm = connection.prepareStatement(query);
            stm.setInt(1, courseId);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public int totalnumberOfPassedStudent() {
        String query = "SELECT COUNT(*) FROM CourseRegistration WHERE Status=true";
        try {
            stm = connection.prepareStatement(query);

            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public int totalnumberOfFailedStudent() {
        String query = "SELECT COUNT(*) FROM CourseRegistration WHERE Status=false";
        try {
            stm = connection.prepareStatement(query);

            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public int numberOfRegistrationsThisMonth() {
        int count = 0;
        try {
            //  Get first and last day of current month
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Date firstDayOfMonth = calendar.getTime();
            calendar.add(Calendar.MONTH, 1);
            calendar.add(Calendar.DATE, -1);
            Date lastDayOfMonth = calendar.getTime();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String firstDayOfMonthStr = sdf.format(firstDayOfMonth);
            String lastDayOfMonthStr = sdf.format(lastDayOfMonth);

            String query = "SELECT COUNT(*) FROM CourseRegistration WHERE RegisterTime >= ? AND RegisterTime <= ?";
            stm = connection.prepareStatement(query);
            stm.setString(1, firstDayOfMonthStr);
            stm.setString(2, lastDayOfMonthStr);

            rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public boolean registerCourse(int userId, int courseId) {
        String checkQuery = "SELECT * FROM CourseRegistration WHERE UserId=? AND CourseId=? AND Status IS NULL";
        try {
            PreparedStatement checkStm = connection.prepareStatement(checkQuery);
            checkStm.setInt(1, userId);
            checkStm.setInt(2, courseId);
            rs = checkStm.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

        String insertQuery = "INSERT INTO CourseRegistration (UserId, CourseId, Status) VALUES (?, ?, NULL)";
        try {
            PreparedStatement insertStm = connection.prepareStatement(insertQuery);
            insertStm.setInt(1, userId);
            insertStm.setInt(2, courseId);
            return insertStm.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Retrieve a list of courses registered by a user with a specified status.
     *
     * @param userId The ID of the user whose registered courses are to be
     * retrieved.
     * @param courseStatus The status of the courses to retrieve. False
     * indicates Ongoing, True indicates Done.
     * @return A list of Course objects representing the registered courses.
     */
    public List<Course> registeredCourses(int userId, Boolean courseStatus) {
        List<Course> courseList = new ArrayList<>();
        UserDAO userDAO = new UserDAO();
        DimensionDAO dimensionDAO = new DimensionDAO();
        String strSelect = "SELECT * "
                + "FROM Course c JOIN CourseRegistration r "
                + "ON c.Id = r.CourseId "
                + "WHERE r.UserId =? AND r.Status=?";
        try {
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, userId);
            stm.setObject(2, courseStatus);
            rs = stm.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("c.Id"));
                course.setSubjectCode(rs.getString("SubjectCode"));
                course.setTitle(rs.getString("Title"));
                course.setDescription(rs.getString("Description"));
                course.setThumbnail(rs.getString("Thumbnail"));
                course.setNumberOfParticipants(rs.getInt("NumberOfParticipants"));
                course.setDimension(dimensionDAO.getById(rs.getInt("DimensionId")));
                course.setOwner(userDAO.getById(rs.getInt("OwnerId")));
                course.setStatus(rs.getBoolean("Status"));
                course.setFeatured(rs.getBoolean("Featured"));
                courseList.add(course);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            userDAO.closeConnection();
            dimensionDAO.closeConnection();
        }
        return courseList;
    }

    public List<Course> pendingCourses(int userId) {
        List<Course> courseList = new ArrayList<>();
        UserDAO userDAO = new UserDAO();
        DimensionDAO dimensionDAO = new DimensionDAO();
        String strSelect = "SELECT * "
                + "FROM Course c JOIN CourseRegistration r "
                + "ON c.Id = r.CourseId "
                + "WHERE r.UserId =? AND r.Status IS NULL";
        try {
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, userId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("c.Id"));
                course.setSubjectCode(rs.getString("SubjectCode"));
                course.setTitle(rs.getString("Title"));
                course.setDescription(rs.getString("Description"));
                course.setThumbnail(rs.getString("Thumbnail"));
                course.setNumberOfParticipants(rs.getInt("NumberOfParticipants"));
                course.setDimension(dimensionDAO.getById(rs.getInt("DimensionId")));
                course.setOwner(userDAO.getById(rs.getInt("OwnerId")));
                course.setStatus(rs.getBoolean("Status"));
                course.setFeatured(rs.getBoolean("Featured"));
                courseList.add(course);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            userDAO.closeConnection();
            dimensionDAO.closeConnection();
        }
        return courseList;
    }

}
