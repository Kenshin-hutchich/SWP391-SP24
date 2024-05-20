/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dtos.UserUpdateDto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Setting;
import model.User;
import services.HashService;
import services.IHashService;

/**
 *
 * @author KENSHIN
 */
public class UserDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;
    IHashService hashService = new HashService();
    
    public boolean isEmailExisted(String email){
        boolean isExist = false;
        try {
            String strSelect = "SELECT COUNT(*) FROM User WHERE Email=?";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                isExist = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        return isExist;
    }
    
    public boolean isMobileExisted(String mobile){
        boolean isExist = false;
        try {
            String strSelect = "SELECT COUNT(*) FROM User WHERE Mobile=?";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, mobile);
            rs = stm.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                isExist = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        return isExist;
    }

    public List<User> getPaginated(int startIndex, int tableSize, int searchRoleId, Boolean statusValue, Timestamp startTimestamp, Timestamp endTimestamp) {
        List<User> userList = new ArrayList<>();
        SettingDAO settingDAO = new SettingDAO();
        try {
            StringBuilder strSelect = new StringBuilder("SELECT * FROM User WHERE 1=1");

            // Thêm các điều kiện tìm kiếm vào câu query
            if (searchRoleId > 0) {
                strSelect.append(" AND RoleSettingId = ?");
            }
            if (statusValue != null) {
                strSelect.append(" AND Status = ?");
            }
            if (startTimestamp != null) {
                strSelect.append(" AND CreatedAt >= ?");
            }
            if (endTimestamp != null) {
                strSelect.append(" AND CreatedAt <= ?");
            }
            // Thêm phân trang và mặc định hiển thị mới nhất ở đầu
            strSelect.append(" ORDER BY CreatedAt DESC LIMIT ?, ?");

            stm = connection.prepareStatement(strSelect.toString());

            // Đặt giá trị cho các tham số
            int paramIndex = 1;
            if (searchRoleId > 0) {
                stm.setInt(paramIndex++, searchRoleId);
            }
            if (statusValue != null) {
                stm.setBoolean(paramIndex++, statusValue);
            }
            if (startTimestamp != null) {
                stm.setTimestamp(paramIndex++, startTimestamp);
            }
            if (endTimestamp != null) {
                stm.setTimestamp(paramIndex++, endTimestamp);
            }
            // Đặt giá trị cho phân trang
            stm.setInt(paramIndex++, startIndex);
            stm.setInt(paramIndex, tableSize);

            rs = stm.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("Id"));
                user.setEmail(rs.getString("Email"));
                user.setHash(rs.getString("Hash"));
                user.setName(rs.getString("Name"));
                user.setMobile(rs.getString("Mobile"));
                user.setGender(rs.getBoolean("Gender"));
                user.setCreatedAt(rs.getTimestamp("CreatedAt"));
                user.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                user.setStatus(rs.getBoolean("Status"));
                user.setAvatar(rs.getString("Avatar"));
                user.setRoleSetting(settingDAO.getById(rs.getInt("RoleSettingId")));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            settingDAO.closeConnection();
        }
        return userList;
    }

    public int getSize(int searchRoleId, Boolean statusValue, Timestamp startTimestamp, Timestamp endTimestamp) {
        int size = 0;
        SettingDAO settingDAO = new SettingDAO();
        try {
            StringBuilder strSelect = new StringBuilder("SELECT COUNT(*) FROM User WHERE 1=1");

            // Thêm các điều kiện tìm kiếm vào câu query
            if (searchRoleId > 0) {
                strSelect.append(" AND RoleSettingId = ?");
            }
            if (statusValue != null) {
                strSelect.append(" AND Status = ?");
            }
            if (startTimestamp != null) {
                strSelect.append(" AND CreatedAt >= ?");
            }
            if (endTimestamp != null) {
                strSelect.append(" AND CreatedAt <= ?");
            }
            // Thêm phân trang và mặc định hiển thị mới nhất ở đầu
            stm = connection.prepareStatement(strSelect.toString());

            // Đặt giá trị cho các tham số
            int paramIndex = 1;
            if (searchRoleId > 0) {
                stm.setInt(paramIndex++, searchRoleId);
            }
            if (statusValue != null) {
                stm.setBoolean(paramIndex++, statusValue);
            }
            if (startTimestamp != null) {
                stm.setTimestamp(paramIndex++, startTimestamp);
            }
            if (endTimestamp != null) {
                stm.setTimestamp(paramIndex++, endTimestamp);
            }

            rs = stm.executeQuery();

            if (rs.next()) {
                size = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            settingDAO.closeConnection();
        }
        return size;
    }
    
    // Get all Users
    public List<User> getAll() {
        SettingDAO settingDAO = new SettingDAO();
        List<User> userList = new ArrayList<>();
        try {
            String strSelect = "SELECT * FROM User";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("Id"));
                user.setEmail(rs.getString("Email"));
                user.setHash(rs.getString("Hash"));
                user.setName(rs.getString("Name"));
                user.setMobile(rs.getString("Mobile"));
                user.setGender(rs.getBoolean("Gender"));
                user.setCreatedAt(rs.getTimestamp("CreatedAt"));
                user.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                user.setStatus(rs.getBoolean("Status"));
                user.setAvatar(rs.getString("Avatar"));
                user.setRoleSetting(settingDAO.getById(rs.getInt("RoleSettingId")));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            settingDAO.closeConnection();
        }
        return userList;
    }

    // Get paginated list of user
    public List<User> getListByPage(List<User> list, int start, int end) {
        List<User> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public int getRegisteredUsersCountToday() {
        int registeredUsersCount = 0;
        try {
            LocalDate today = LocalDate.now();

            String query = "SELECT COUNT(*) AS user_count FROM User WHERE CreatedAt >= ? AND CreatedAt < ?";
            stm = connection.prepareStatement(query);
            stm.setTimestamp(1, Timestamp.valueOf(today.atStartOfDay()));
            stm.setTimestamp(2, Timestamp.valueOf(today.plusDays(1).atStartOfDay()));

            rs = stm.executeQuery();
            if (rs.next()) {
                registeredUsersCount = rs.getInt("user_count");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return registeredUsersCount;
    }

    public int getRegisteredUsersCountThisMonth() {
        int registeredUsersCount = 0;
        try {
            // Lấy ngày đầu tiên của tháng hiện tại
            LocalDate firstDayOfCurrentMonth = LocalDate.now().withDayOfMonth(1);
            // Lấy ngày cuối cùng của tháng hiện tại
            LocalDate lastDayOfCurrentMonth = YearMonth.now().atEndOfMonth();

            // Tạo truy vấn SQL để đếm số lượng người dùng được tạo trong tháng này
            String query = "SELECT COUNT(*) AS user_count FROM User WHERE CreatedAt >= ? AND CreatedAt <= ?";
            stm = connection.prepareStatement(query);
            stm.setTimestamp(1, Timestamp.valueOf(firstDayOfCurrentMonth.atStartOfDay()));
            stm.setTimestamp(2, Timestamp.valueOf(lastDayOfCurrentMonth.atTime(23, 59, 59)));

            rs = stm.executeQuery();
            if (rs.next()) {
                registeredUsersCount = rs.getInt("user_count");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return registeredUsersCount;
    }

    public int getTotal() {
        int userCount = 0;
        try {
            String query = "SELECT COUNT(*) AS user_count FROM User";
            stm = connection.prepareStatement(query);
            rs = stm.executeQuery();
            if (rs.next()) {
                userCount = rs.getInt("user_count");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return userCount;
    }
    
    public int getTotal(Setting roleSetting) {
        int userCount = 0;
        try {
            String query = "SELECT COUNT(*) AS user_count FROM User WHERE RoleSettingId=?";
            stm = connection.prepareStatement(query);
            stm.setInt(1, roleSetting.getId());
            rs = stm.executeQuery();
            if (rs.next()) {
                userCount = rs.getInt("user_count");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return userCount;
    }

    public double getAverageRegisteredUsersPerMonth() {
        double averageRegisteredUsers = 0.0;
        try {
            LocalDateTime currentDate = LocalDateTime.now();

            String query = "SELECT COUNT(*) AS user_count, MIN(CreatedAt) AS min_created_at FROM User WHERE CreatedAt <= ?";
            stm = connection.prepareStatement(query);
            stm.setTimestamp(1, Timestamp.valueOf(currentDate));

            rs = stm.executeQuery();
            if (rs.next()) {
                int totalRegisteredUsers = rs.getInt("user_count");
                LocalDateTime minCreatedAt = rs.getTimestamp("min_created_at").toLocalDateTime();

                long monthsBetween = ChronoUnit.MONTHS.between(minCreatedAt.toLocalDate().withDayOfMonth(1), currentDate.toLocalDate().withDayOfMonth(1)) + 1;
                averageRegisteredUsers = (double) totalRegisteredUsers / monthsBetween;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return averageRegisteredUsers;
    }

    public List<User> getByCourseId(int courseId) {
        List<User> userList = new ArrayList<>();
        SettingDAO settingDAO = new SettingDAO();
        try {
            String strSelect = "SELECT u.* "
                    + "FROM User u JOIN CourseRegistration r "
                    + "ON u.Id = r.UserId "
                    + "WHERE r.CourseId = ? AND c.Status=false";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, courseId);
            rs = stm.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("Id"));
                user.setEmail(rs.getString("Email"));
                user.setHash(rs.getString("Hash"));
                user.setName(rs.getString("Name"));
                user.setMobile(rs.getString("Mobile"));
                user.setGender(rs.getBoolean("Gender"));
                user.setCreatedAt(rs.getTimestamp("CreatedAt"));
                user.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                user.setStatus(rs.getBoolean("Status"));
                user.setAvatar(rs.getString("Avatar"));
                user.setRoleSetting(settingDAO.getById(rs.getInt("RoleSettingId")));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            settingDAO.closeConnection();
        }
        return userList;
    }

    // Get User by ID
    public User getById(int userId) {
        SettingDAO settingDAO = new SettingDAO();
        User user = new User();
        try {
            String strSelect = "SELECT * FROM User WHERE Id=?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, userId);
            rs = stm.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("Id"));
                user.setEmail(rs.getString("Email"));
                user.setHash(rs.getString("Hash"));
                user.setName(rs.getString("Name"));
                user.setMobile(rs.getString("Mobile"));
                user.setGender(rs.getBoolean("Gender"));
                user.setCreatedAt(rs.getTimestamp("CreatedAt"));
                user.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                user.setStatus(rs.getBoolean("Status"));
                user.setAvatar(rs.getString("Avatar"));
                user.setRoleSetting(settingDAO.getById(rs.getInt("RoleSettingId")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            settingDAO.closeConnection();
        }
        return user;
    }

    // Get User by Email
    public User getByEmail(String email) {
        SettingDAO settingDAO = new SettingDAO();
        User user = new User();
        try {
            String strSelect = "SELECT * FROM User WHERE Email=?";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("Id"));
                user.setEmail(rs.getString("Email"));
                user.setHash(rs.getString("Hash"));
                user.setName(rs.getString("Name"));
                user.setMobile(rs.getString("Mobile"));
                user.setGender(rs.getBoolean("Gender"));
                user.setCreatedAt(rs.getTimestamp("CreatedAt"));
                user.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                user.setStatus(rs.getBoolean("Status"));
                user.setAvatar(rs.getString("Avatar"));
                user.setRoleSetting(settingDAO.getById(rs.getInt("RoleSettingId")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            settingDAO.closeConnection();
        }
        return user;
    }

    /**
     * Update user profile information.
     *
     * @param user The User object containing updated profile information.
     * @return true if the user profile is updated successfully, otherwise
     * false.
     */
    public boolean updateUserProfile(User user) {
        // Check if new phone number is duplicated
        try {
            String sqlValidation = "SELECT COUNT(*) FROM User WHERE Id != ? AND Mobile = ?";
            stm = connection.prepareStatement(sqlValidation);
            stm.setInt(1, user.getId());
            stm.setString(2, user.getMobile());
            rs = stm.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) > 0) {
                    System.out.println("Duplicated phone number.");
                    return false;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        // Continue with updating user information
        // Update User profile
        try {
            String strUpdate = "UPDATE User SET Name=?, Gender=?, Mobile=?, Avatar=?, UpdatedAt=? WHERE Id=?";
            stm = connection.prepareStatement(strUpdate);
            stm.setString(1, user.getName());
            stm.setBoolean(2, user.getGender());
            stm.setString(3, user.getMobile());
            stm.setString(4, user.getAvatar());
            stm.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            stm.setInt(6, user.getId());
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
    /**
     * Update user profile information. (for admin)
     *
     * @param user The User object containing updated profile information.
     * @return true if the user profile is updated successfully, otherwise
     * false.
     */
    public boolean updateUserProfile(UserUpdateDto user) {
        // Check if new phone number is duplicated
        try {
            String sqlValidation = "SELECT COUNT(*) FROM User WHERE Id != ? AND Mobile = ?";
            stm = connection.prepareStatement(sqlValidation);
            stm.setInt(1, user.getId());
            stm.setString(2, user.getMobile());
            rs = stm.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) > 0) {
                    System.out.println("Duplicated phone number.");
                    return false;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        // Continue with updating user information
        // Update User profile
        try {
            String strUpdate = "UPDATE User SET Name=?, Gender=?, Mobile=?, Status=?, UpdatedAt=?, RoleSettingId=? WHERE Id=?";
            stm = connection.prepareStatement(strUpdate);
            stm.setString(1, user.getName());
            stm.setBoolean(2, user.getGender());
            stm.setString(3, user.getMobile());
            stm.setBoolean(4, user.getStatus());
            stm.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            stm.setInt(6, user.getRoleSetting().getId());
            stm.setInt(7, user.getId());
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * Update user password.
     *
     * @param userId The ID of the user whose password needs to be updated.
     * @param oldPassword The current password of the user.
     * @param newPassword The new password to be set for the user.
     * @return true if the user password is updated successfully, otherwise
     * false.
     */
    public boolean updateUserPassword(int userId, String oldPassword, String newPassword) {
        try {
            String strValidate = "SELECT Hash FROM User WHERE Id = ? ";
            stm = connection.prepareStatement(strValidate);
            stm.setInt(1, userId);
            rs = stm.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("Hash");
                if (hashService.Verify(oldPassword, storedHash)) {
                    // Update User password
                    String strUpdate = "UPDATE User SET Hash = ?, UpdatedAt = ? WHERE Id = ?";
                    stm = connection.prepareStatement(strUpdate);
                    stm.setString(1, hashService.HashPassword(newPassword));
                    stm.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                    stm.setInt(3, userId);
                    return stm.executeUpdate() > 0;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    // Insert a new User into the database
    // Return true if User is inserted successfully, otherwise return false
    public boolean insert(User user) {
        // Check if new phone number is duplicated
        if (user.getMobile() != null && !user.getMobile().isEmpty()) {
            try {
                String sqlValidation = "SELECT COUNT(*) FROM User WHERE Mobile = ?";
                stm = connection.prepareStatement(sqlValidation);
                stm.setString(1, user.getMobile());
                rs = stm.executeQuery();
                if (rs.next()) {
                    if (rs.getInt(1) > 0) {
                        System.out.println("Duplicated phone number.");
                        return false;
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex);
                return false;
            }
        }
        // Check if new email is duplicated
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            try {
                String sqlValidation = "SELECT COUNT(*) FROM User WHERE Email = ?";
                stm = connection.prepareStatement(sqlValidation);
                stm.setString(1, user.getMobile());
                rs = stm.executeQuery();
                if (rs.next()) {
                    if (rs.getInt(1) > 0) {
                        System.out.println("Duplicated email.");
                        return false;
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex);
                return false;
            }
        }
        // Insert new User into the database
        try {
            String strInsert = "INSERT INTO User (Email, Hash, Name, Mobile, Gender, Status, Avatar, RoleSettingId, CreatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stm = connection.prepareStatement(strInsert);
            stm.setString(1, user.getEmail());
            stm.setString(2, user.getHash());
            stm.setString(3, user.getName());
            stm.setString(4, user.getMobile());
            stm.setBoolean(5, user.getGender());
            stm.setBoolean(6, user.getStatus());
            stm.setString(7, user.getAvatar());
            stm.setInt(8, user.getRoleSetting().getId());
            stm.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean update2(User user) {

        // Update User informations
        try {
            String strUpdate = "UPDATE User SET Hash=?, Name=?, Mobile=?, Gender=?, Status=?, Avatar=?, RoleSettingId=? WHERE Id=?";
            stm = connection.prepareStatement(strUpdate);
            stm.setString(1, user.getHash());
            stm.setString(2, user.getName());
            stm.setString(3, user.getMobile());
            stm.setBoolean(4, user.getGender());
            stm.setBoolean(5, user.getStatus());
            stm.setString(6, user.getAvatar());
            stm.setInt(7, user.getRoleSetting().getId());
            stm.setInt(8, user.getId());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public List<User> search(String name, String email, String mobile) {
        SettingDAO settingDAO = new SettingDAO();
        List<User> list = new ArrayList<>();
        String query = "SELECT * FROM swp391_lms.User where 1=1 ";
        if (name != null && !name.equals("")) {
            query += "and `Name` like '%" + name + "%'";
        }
        if (email != null && !email.equals("")) {
            query += " and `Email` like '%" + email + "%'";
        }
        if (mobile != null && !mobile.equals("")) {
            query += " and `Mobile` like '%" + mobile + "%'";
        }

        try {
            stm = connection.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("Id"));
                user.setEmail(rs.getString("Email"));
                user.setHash(rs.getString("Hash"));
                user.setName(rs.getString("Name"));
                user.setMobile(rs.getString("Mobile"));
                user.setGender(rs.getBoolean("Gender"));
                user.setStatus(rs.getBoolean("Status"));
                user.setAvatar(rs.getString("Avatar"));
                user.setRoleSetting(settingDAO.getById(rs.getInt("RoleSettingId")));

                list.add(user);

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            settingDAO.closeConnection();
        }

        return list;
    }

    public List<User> checkUser(int[] gender, int[] status, int[] role) {
        SettingDAO settingDAO = new SettingDAO();
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM swp391_lms.user where 1=1";
        if (gender != null) {
            sql += " and gender in(";
            for (int i = 0; i < gender.length; i++) {
                sql += gender[i] + ",";
            }
            if (sql.endsWith(",")) {
                sql = sql.substring(0, sql.length() - 1);

            }
            sql += ")";
        }
        if (status != null) {
            sql += " and status in(";
            for (int i = 0; i < status.length; i++) {
                sql += status[i] + ",";
            }
            if (sql.endsWith(",")) {
                sql = sql.substring(0, sql.length() - 1);

            }
            sql += ")";
        }
        if (role != null) {
            sql += " and role in(";
            for (int i = 0; i < role.length; i++) {
                sql += role[i] + ",";
            }
            if (sql.endsWith(",")) {
                sql = sql.substring(0, sql.length() - 1);

            }
            sql += ")";
        }

        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("Id"));
                user.setEmail(rs.getString("Email"));
                user.setHash(rs.getString("Hash"));
                user.setName(rs.getString("Name"));
                user.setMobile(rs.getString("Mobile"));
                user.setGender(rs.getBoolean("Gender"));
                user.setStatus(rs.getBoolean("Status"));
                user.setAvatar(rs.getString("Avatar"));
                user.setRoleSetting(settingDAO.getById(rs.getInt("RoleSettingId")));

                list.add(user);

            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            settingDAO.closeConnection();
        }

        return list;
    }

    public boolean insert2(User user) {

        try {
            String strInsert = "INSERT INTO User (Email, Hash, Name, Mobile, Gender, Status, Avatar, RoleSettingId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stm = connection.prepareStatement(strInsert);
            stm.setString(1, user.getEmail());
            stm.setString(2, user.getHash());
            stm.setString(3, user.getName());
            stm.setString(4, user.getMobile());
            stm.setBoolean(5, user.getGender());
            stm.setBoolean(6, user.getStatus());
            stm.setString(7, user.getAvatar());
            stm.setInt(8, user.getRoleSetting().getId());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // Delete an existing User by ID
    // Return true if User is deleted successfully, otherwise return false
    public boolean delete(int userId) {
        try {
            String strDelete = "DELETE FROM User WHERE Id=?";
            stm = connection.prepareStatement(strDelete);
            stm.setInt(1, userId);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // Login by email and password
    // Return User if login successful, otherwise return null
    public User login(String email, String password) {
        SettingDAO set = new SettingDAO();
        try {
            String sql = "Select *from user where Email=? and Hash=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User a = new User();
                a.setId(rs.getInt(1));
                a.setEmail(rs.getString(2));
                a.setHash(rs.getString(3));
                a.setName(rs.getString(4));
                a.setMobile(rs.getString(5));
                a.setGender(rs.getBoolean(6));
                a.setStatus(rs.getBoolean(7));
                a.setAvatar(rs.getString(8));
                a.setRoleSetting(set.getById(rs.getInt(9)));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(model.User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean login1(String email, String password) {
        try {
            String sql = "SELECT Hash FROM User WHERE Email = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                String hash = rs.getString(1);
                boolean check = hashService.Verify(password, hash);
                return check;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void register(String email, String password, String name, String mobile, boolean gender) {
        try {
            String sql = "INSERT INTO `swp391_lms`.`user`\n"
                    + "(\n"
                    + "`Email`,\n"
                    + "`Hash`,\n"
                    + "`Name`,\n"
                    + "`Mobile`,\n"
                    + "`Gender`,\n"
                    + "`Status`,\n"
                    + "`RoleSettingId`)\n"
                    + "VALUES\n"
                    + "(?,?,?,?,?,1,2);";

            stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            stm.setString(3, name);
            stm.setString(4, mobile);
            stm.setBoolean(5, gender);

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(model.User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Update user's password
    public void resetPass(String password, String email) {
        try {
            String sql = "UPDATE `swp391_lms`.`user`\n"
                    + "SET\n"
                    + "`Hash` = ?\n"
                    + "WHERE `Email` = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, password);
            stm.setString(2, email);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(model.User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
