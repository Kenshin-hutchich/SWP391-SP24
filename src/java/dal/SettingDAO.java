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
import model.Setting;

/**
 *
 * @author KENSHIN
 */
public class SettingDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    // Get all Settings
    public List<Setting> getAll() {
        try {
            String strSelect = "SELECT * FROM Setting";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            List<Setting> settingList = new ArrayList<>();
            while (rs.next()) {
                Setting setting = new Setting();
                setting.setId(rs.getInt("Id"));
                setting.setName(rs.getString("Name"));
                setting.setValue(rs.getString("Value"));
                setting.setOrder(rs.getInt("InOrder"));
                setting.setStatus(rs.getBoolean("Status"));
                settingList.add(setting);
            }
            if (settingList.isEmpty()) {
                return null;
            } else {
                return settingList;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Setting> getListByPage(List<Setting> list,
            int start, int end) {
        List<Setting> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public List<Setting> checkUser(String[] name, int[] status) {
        List<Setting> list = new ArrayList<>();
        String sql = "SELECT * FROM swp391_lms.setting where 1=1";
        if (name != null) {
            sql += " and name in(' ";
            for (int i = 0; i < name.length; i++) {
                sql += name[i] + "','";
            }
            if (sql.endsWith("'")) {
                sql = sql.substring(0, sql.length() - 2);

            }
            sql += " )";
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
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Setting setting = new Setting();
                setting.setId(rs.getInt("Id"));
                setting.setName(rs.getString("Name"));
                setting.setValue(rs.getString("Value"));
                setting.setOrder(rs.getInt("InOrder"));
                setting.setStatus(rs.getBoolean("Status"));

                list.add(setting);

            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Setting> search(String name, String value) {

        List<Setting> list = new ArrayList<>();
        String query = "SELECT * FROM swp391_lms.Setting where 1=1 ";
        if (name != null && !name.equals("")) {
            query += "and `Name` like '%" + name + "%'";
        }
        if (value != null && !value.equals("")) {
            query += " and `Value` like '%" + value + "%'";
        }

        try {
            stm = connection.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                Setting setting = new Setting();
                setting.setId(rs.getInt("Id"));
                setting.setName(rs.getString("Name"));
                setting.setValue(rs.getString("Value"));
                setting.setOrder(rs.getInt("InOrder"));
                setting.setStatus(rs.getBoolean("Status"));
                list.add(setting);

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return list;
    }

    // Get all Settings by Name
    public List<Setting> getByName(String name) {
        try {
            String strSelect = "SELECT * FROM Setting WHERE Name=?";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, name);
            rs = stm.executeQuery();
            List<Setting> settingList = new ArrayList<>();
            while (rs.next()) {
                Setting setting = new Setting();
                setting.setId(rs.getInt("Id"));
                setting.setName(rs.getString("Name"));
                setting.setValue(rs.getString("Value"));
                setting.setOrder(rs.getInt("InOrder"));
                setting.setStatus(rs.getBoolean("Status"));
                settingList.add(setting);
            }
            if (settingList.isEmpty()) {
                return null;
            } else {
                return settingList;
            }
        } catch (SQLException e) {
            System.out.println("Function getByName");
            System.out.println(e);
            return null;
        } 
    }

    // Get all Settings by Name and Order
    public List<Setting> getByNameAndOrder(String name, int order) {
        try {
            String strSelect = "SELECT * FROM Setting WHERE Name=? AND InOrder=?";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, name);
            stm.setInt(2, order);
            rs = stm.executeQuery();
            List<Setting> settingList = new ArrayList<>();
            while (rs.next()) {
                Setting setting = new Setting();
                setting.setId(rs.getInt("Id"));
                setting.setName(rs.getString("Name"));
                setting.setValue(rs.getString("Value"));
                setting.setOrder(rs.getInt("InOrder"));
                setting.setStatus(rs.getBoolean("Status"));
                settingList.add(setting);
            }
            if (settingList.isEmpty()) {
                return null;
            } else {
                return settingList;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } 
    }

    public int getMaxOrderByName(String name) {
        int maxOrder = 1;
        try {
            String strSelect = "SELECT MAX(InOrder) AS MaxInOrder FROM Setting WHERE Name=?";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, name);
            rs = stm.executeQuery();
            if (rs.next()) {
                maxOrder = rs.getInt("MaxInOrder");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } 
        return maxOrder;
    }

    public Setting getByValue(String value) {
        try {
            String strSelect = "SELECT * FROM Setting WHERE Value=?";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, value);
            rs = stm.executeQuery();

            if (rs.next()) {
                Setting setting = new Setting();
                setting.setId(rs.getInt("Id"));
                setting.setName(rs.getString("Name"));
                setting.setValue(rs.getString("Value"));
                setting.setOrder(rs.getInt("InOrder"));
                setting.setStatus(rs.getBoolean("Status"));
                return setting;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Get a Setting by Value and Name
    public Setting getByNameAndValue(String name, String value) {
        Setting setting = new Setting();
        try {
            String strSelect = "SELECT * FROM Setting WHERE Name=? AND Value=?";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, name);
            stm.setString(2, value);
            rs = stm.executeQuery();
            if (rs.next()) {
                setting.setId(rs.getInt("Id"));
                setting.setName(rs.getString("Name"));
                setting.setValue(rs.getString("Value"));
                setting.setOrder(rs.getInt("InOrder"));
                setting.setStatus(rs.getBoolean("Status"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } 
        return setting;
    }

    // Get Setting by ID
    public Setting getById(int settingId) {
        Setting setting = new Setting();
        try {
            String strSelect = "SELECT * FROM Setting WHERE Id=?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, settingId);
            rs = stm.executeQuery();
            if (rs.next()) {
                setting.setId(rs.getInt("Id"));
                setting.setName(rs.getString("Name"));
                setting.setValue(rs.getString("Value"));
                setting.setOrder(rs.getInt("InOrder"));
                setting.setStatus(rs.getBoolean("Status"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return setting;
    }

    // Create a new Setting
    public boolean create(Setting setting) {
        try {
            String strInsert = "INSERT INTO Setting (Name, Value,InOrder, Status) VALUES (?,?, ?, ?)";
            stm = connection.prepareStatement(strInsert);
            stm.setString(1, setting.getName());
            stm.setString(2, setting.getValue());
            stm.setInt(3, setting.getOrder());
            stm.setBoolean(4, setting.getStatus());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // Update an existing Setting
    public boolean update(Setting setting) {
        try {
            String strUpdate = "UPDATE Setting SET Name=?, Value=?,InOrder=?, Status=? WHERE Id=?";
            stm = connection.prepareStatement(strUpdate);
            stm.setString(1, setting.getName());
            stm.setString(2, setting.getValue());
            stm.setInt(3, setting.getOrder());
            stm.setBoolean(4, setting.getStatus());
            stm.setInt(5, setting.getId());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // Delete an existing Setting by ID
    public boolean delete(int settingId) {
        try {
            String strDelete = "DELETE FROM Setting WHERE Id=?";
            stm = connection.prepareStatement(strDelete);
            stm.setInt(1, settingId);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
