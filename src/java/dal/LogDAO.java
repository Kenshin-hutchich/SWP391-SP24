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
import model.Log;
import model.Setting;
import model.User;

/**
 *
 * @author KENSHIN
 */
public class LogDAO extends DBContext {

    private PreparedStatement stm;
    private ResultSet rs;

    // Get a specific Logs
    public Log getSpecific(int actorId, int actionTypeSettingId, int targetTypeSettingId, int targetId) {
        System.out.println("USE LOGDAO");
        UserDAO userDAO = new UserDAO();
        SettingDAO settingDAO = new SettingDAO();
        Log log = new Log();
        try {
            String strSelect = "SELECT * FROM Log WHERE ActorId=? AND ActionSettingId=? AND TargetTypeSettingId=? AND TargetId=?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, actorId);
            stm.setInt(2, actionTypeSettingId);
            stm.setInt(3, targetTypeSettingId);
            stm.setInt(4, targetId);
            rs = stm.executeQuery();
            if (rs.next()) {
                log.setId(rs.getInt("Id"));
                log.setActor(userDAO.getById(rs.getInt("ActorId")));
                log.setActionSetting(settingDAO.getById(rs.getInt("ActionSettingId")));
                log.setTargetTypeSetting(settingDAO.getById(rs.getInt("TargetTypeSettingId")));
                log.setTargetId(rs.getInt("TargetId"));
                log.setTime(rs.getTimestamp("Time"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            userDAO.closeConnection();
            settingDAO.closeConnection();
        }
        return log;
    }

    // Get all Logs
    public List<Log> getAll() {
        UserDAO userDAO = new UserDAO();
        SettingDAO settingDAO = new SettingDAO();
        List<Log> logList = new ArrayList<>();
        try {
            String strSelect = "SELECT * FROM Log";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Log log = new Log();
                log.setId(rs.getInt("Id"));
                log.setActor(userDAO.getById(rs.getInt("ActorId")));
                log.setActionSetting(settingDAO.getById(rs.getInt("ActionSettingId")));
                log.setTargetTypeSetting(settingDAO.getById(rs.getInt("TargetTypeSettingId")));
                log.setTargetId(rs.getInt("TargetId"));
                log.setTime(rs.getTimestamp("Time"));
                logList.add(log);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            userDAO.closeConnection();
            settingDAO.closeConnection();
        }
        return logList;
    }

    // Get all Logs by action
    public List<Log> getByAction(Setting actionSetting) {
        UserDAO userDAO = new UserDAO();
        SettingDAO settingDAO = new SettingDAO();
        List<Log> logList = new ArrayList<>();
        try {
            String strSelect = "SELECT * FROM Log WHERE ActionSettingId=?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, actionSetting.getId());
            rs = stm.executeQuery();
            while (rs.next()) {
                Log log = new Log();
                log.setId(rs.getInt("Id"));
                log.setActor(userDAO.getById(rs.getInt("ActorId")));
                log.setActionSetting(settingDAO.getById(rs.getInt("ActionSettingId")));
                log.setTargetTypeSetting(settingDAO.getById(rs.getInt("TargetTypeSettingId")));
                log.setTargetId(rs.getInt("TargetId"));
                log.setTime(rs.getTimestamp("Time"));
                logList.add(log);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            userDAO.closeConnection();
            settingDAO.closeConnection();
        }
        return logList;
    }

    // Get all Logs by actor
    public List<Log> getByActor(User actor) {
        UserDAO userDAO = new UserDAO();
        SettingDAO settingDAO = new SettingDAO();
        List<Log> logList = new ArrayList<>();
        try {
            String strSelect = "SELECT * FROM Log WHERE ActorId=?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, actor.getId());
            rs = stm.executeQuery();
            while (rs.next()) {
                Log log = new Log();
                log.setId(rs.getInt("Id"));
                log.setActor(userDAO.getById(rs.getInt("ActorId")));
                log.setActionSetting(settingDAO.getById(rs.getInt("ActionSettingId")));
                log.setTargetTypeSetting(settingDAO.getById(rs.getInt("TargetTypeSettingId")));
                log.setTargetId(rs.getInt("TargetId"));
                log.setTime(rs.getTimestamp("Time"));
                logList.add(log);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            userDAO.closeConnection();
            settingDAO.closeConnection();
        }
        return logList;
    }

    // Get all Logs by target type
    public List<Log> getByTargetType(Setting targetTypeSetting) {
        UserDAO userDAO = new UserDAO();
        SettingDAO settingDAO = new SettingDAO();
        List<Log> logList = new ArrayList<>();
        try {
            String strSelect = "SELECT * FROM Log WHERE TargetTypeSettingId=?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, targetTypeSetting.getId());
            rs = stm.executeQuery();
            while (rs.next()) {
                Log log = new Log();
                log.setId(rs.getInt("Id"));
                log.setActor(userDAO.getById(rs.getInt("ActorId")));
                log.setActionSetting(settingDAO.getById(rs.getInt("ActionSettingId")));
                log.setTargetTypeSetting(settingDAO.getById(rs.getInt("TargetTypeSettingId")));
                log.setTargetId(rs.getInt("TargetId"));
                log.setTime(rs.getTimestamp("Time"));
                logList.add(log);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            userDAO.closeConnection();
            settingDAO.closeConnection();
        }
        return logList;
    }

    // Filter logs by action
    public List<Log> filterByAction(List<Log> logList, Setting actionSetting) {
        List<Log> filteredLogs = new ArrayList<>();
        for (Log log : logList) {
            if (log.getActionSetting().getId() == actionSetting.getId()) {
                filteredLogs.add(log);
            }
        }
        return filteredLogs.isEmpty() ? null : filteredLogs;
    }

    // Filter logs by actor
    public List<Log> filterByActor(List<Log> logList, User actor) {
        List<Log> filteredLogs = new ArrayList<>();
        for (Log log : logList) {
            if (log.getActor().getId() == actor.getId()) {
                filteredLogs.add(log);
            }
        }
        return filteredLogs.isEmpty() ? null : filteredLogs;
    }

    // Filter logs by target type
    public List<Log> filterByTargetType(List<Log> logList, Setting targetTypeSetting) {
        List<Log> filteredLogs = new ArrayList<>();
        for (Log log : logList) {
            if (log.getTargetTypeSetting().getId() == targetTypeSetting.getId()) {
                filteredLogs.add(log);
            }
        }
        return filteredLogs.isEmpty() ? null : filteredLogs;
    }

    // Get log by target Id from a logs list
    // Alert: logs list must be filtered by target type first
    public Log getByTargetId(List<Log> list, int targetId) {
        for (Log log : list) {
            if (log.getTargetId() == targetId) {
                return log;
            }
        }
        return null;
    }

    // Create a new Log
    public boolean create(Log log) {
        try {
            String strInsert = "INSERT INTO Log (ActorId, ActionSettingId, TargetTypeSettingId, TargetId, Time) VALUES (?, ?, ?, ?, ?)";
            stm = connection.prepareStatement(strInsert);
            stm.setInt(1, log.getActor().getId());
            stm.setInt(2, log.getActionSetting().getId());
            stm.setInt(3, log.getTargetTypeSetting().getId());
            stm.setInt(4, log.getTargetId());
            stm.setTimestamp(5, log.getTime());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
