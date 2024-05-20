/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import model.AccessLog;

/**
 *
 * @author KENSHIN
 */
public class AccessLogDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    public boolean insert(AccessLog accessLog) {
        String strInsert = "INSERT INTO AccessLogs (AccessTime, IpAddress, PageAccessedId) VALUES (?, ?, ?)";
        try {
            stm = connection.prepareStatement(strInsert);
            stm.setObject(1, accessLog.getAccessTime());
            stm.setString(2, accessLog.getIpAddress());
            stm.setInt(3, accessLog.getPageAccessed().getId());
            return stm.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    public List<AccessLog> getByDay(LocalDate date) {
        PageDAO pageDAO = new PageDAO();
        List<AccessLog> accessLogs = new ArrayList<>();
        String strSelect = "SELECT * FROM AccessLogs WHERE DATE(AccessTime) = ?";
        try {
            stm = connection.prepareStatement(strSelect);
            stm.setObject(1, date);
            rs = stm.executeQuery();
            while (rs.next()) {
                AccessLog accessLog = new AccessLog();
                accessLog.setId(rs.getInt("Id"));
                accessLog.setAccessTime(rs.getTimestamp("AccessTime").toLocalDateTime());
                accessLog.setIpAddress(rs.getString("IpAddress"));
                accessLog.setPageAccessed(pageDAO.getById(rs.getInt("PageAccessedId")));
                accessLogs.add(accessLog);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            pageDAO.closeConnection();
        }
        return accessLogs;
    }

    public int countDistinctIPs() {
        int count = 0;
        String strSelect = "SELECT COUNT(DISTINCT IpAddress) AS IpCount FROM AccessLogs";
        try {
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("IpCount");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return count;
    }

    public List<Integer> getByTimePeriod(LocalDateTime startTime, LocalDateTime endTime, String timeUnit) {
        PageDAO pageDAO = new PageDAO();
        List<Integer> accessCounts = new ArrayList<>();

        // Tạo một danh sách các thời điểm trong khoảng thời gian đã cho
        List<LocalDateTime> timePoints = new ArrayList<>();
        LocalDateTime current = startTime;
        while (!current.isAfter(endTime)) {
            timePoints.add(current);
            switch (timeUnit) {
                case "day":
                    current = current.plusDays(1);
                    break;
                case "week":
                    current = current.plusWeeks(1);
                    break;
                case "month":
                    current = current.plusMonths(1);
                    break;
                case "year":
                    current = current.plusYears(1);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid time unit: " + timeUnit);
            }
        }

        // Lấy số lần truy cập cho mỗi thời điểm
        String strSelect = "SELECT COUNT(*) AS accessCount FROM AccessLogs WHERE AccessTime >= ? AND AccessTime <= ?";
        try {
            stm = connection.prepareStatement(strSelect);
            for (LocalDateTime timePoint : timePoints) {
                stm.setObject(1, timePoint);
                stm.setObject(2, timePoint.plus(1, ChronoUnit.DAYS)); // Kết thúc của mỗi ngày là bắt đầu của ngày tiếp theo
                rs = stm.executeQuery();
                if (rs.next()) {
                    accessCounts.add(rs.getInt("accessCount"));
                } else {
                    accessCounts.add(0); // Nếu không có bản ghi nào, số lượng truy cập là 0
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            pageDAO.closeConnection();
        }

        return accessCounts;
    }

}
