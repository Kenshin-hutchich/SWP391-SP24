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
import java.util.Map;
import model.Page;

/**
 *
 * @author KENSHIN
 */
public class PageDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    //  Return every pages in the database
    public List<Page> getAll() {
        SettingDAO settingDAO = new SettingDAO();
        List<Page> pageList = new ArrayList<>();
        String strSelect = "SELECT * FROM Pages";
        try {
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Page func = new Page();
                func.setId(rs.getInt("Id"));
                func.setName(rs.getString("Name"));
                func.setUri(rs.getString("URI"));
                func.setDescription(rs.getString("Description"));
                func.setObjectSetting(settingDAO.getById(rs.getInt("ObjectSettingId")));
                func.setStatus(rs.getBoolean("Status"));
                pageList.add(func);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        settingDAO.closeConnection();
        return pageList;
    }

    //  Return a single function by Id
    public Page getById(int pageId) {
        SettingDAO settingDAO = new SettingDAO();
        Page page = new Page();
        String strSelect = "SELECT * FROM Pages WHERE Id=?";
        try {
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, pageId);
            rs = stm.executeQuery();
            while (rs.next()) {
                page.setId(rs.getInt("Id"));
                page.setName(rs.getString("Name"));
                page.setUri(rs.getString("URI"));
                page.setDescription(rs.getString("Description"));
                page.setObjectSetting(settingDAO.getById(rs.getInt("ObjectSettingId")));
                page.setStatus(rs.getBoolean("Status"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            settingDAO.closeConnection();
        }
        return page;
    }

//  Return a list of pages that can be accessed by role with id roleId and roles with lower priority
    public List<Integer> rolePermissions(int roleId, List<Integer> lowerOrderRoleIds) {
        List<Integer> permissions = new ArrayList<>();
        // Add roleId and lowerOrderRoleIds to a single list
        List<Integer> allRoleIds = new ArrayList<>();
        allRoleIds.addAll(lowerOrderRoleIds);
        allRoleIds.add(roleId);

        // Construct the SQL query dynamically based on the number of role ids
        StringBuilder strSelect = new StringBuilder("SELECT * FROM PageAccess WHERE IsAllowed=true AND (");
        for (int i = 0; i < allRoleIds.size(); i++) {
            strSelect.append("RoleSettingId=?");
            if (i < allRoleIds.size() - 1) {
                strSelect.append(" OR ");
            }
        }
        strSelect.append(")");

        try {
            stm = connection.prepareStatement(strSelect.toString());
            // Set the role ids as parameters in the prepared statement
            for (int i = 0; i < allRoleIds.size(); i++) {
                stm.setInt(i + 1, allRoleIds.get(i));
            }
            rs = stm.executeQuery();
            while (rs.next()) {
                permissions.add(rs.getInt("PageId"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return permissions;
    }

    /**
     * Retrieves a list of pages based on the specified range and filter
     * parameters.
     *
     * @param offset the starting index (inclusive) of the range of records to
     * retrieve
     * @param limit the maximum number of records to retrieve
     * @param filterList a map containing filter parameters (key-value pairs) to
     * apply to the query
     * @return a list of Page objects matching the specified criteria, or null
     * if there is an exception occurred
     */
    public List<Page> getList(int offset, int limit, List<Map<Object, String>> filterList) {
        SettingDAO settingDAO = new SettingDAO();
        List<Page> pageList = new ArrayList<>();
        String strSelect = "SELECT * FROM Pages";
        List<Object> parameters = new ArrayList<>();
        //  Prevent null pointer exception
        if (filterList == null) {
            filterList = new ArrayList<>();
        }
        //  Update SQL command based on the filter list
        if (!filterList.isEmpty()) {
            strSelect += " WHERE ";
            for (int i = 0; i < filterList.size(); i++) {
                strSelect += " ( ";
                int count = 0;
                Map<Object, String> filters = filterList.get(i);
                for (Map.Entry<Object, String> entry : filters.entrySet()) {
                    String value = entry.getValue();
                    strSelect += value + " =? ";
                    count++;
                    if (count < filters.size()) {
                        strSelect += " OR ";
                    }
                    parameters.add(entry.getKey());
                }
                strSelect += " ) ";
                if (i < filterList.size() - 1) {
                    strSelect += " AND ";
                }
            }
        }
        strSelect += " LIMIT ?, ?";
        try {
            stm = connection.prepareStatement(strSelect);
            if (!parameters.isEmpty()) {
                for (int i = 0; i < parameters.size(); i++) {
                    stm.setObject(i + 1, parameters.get(i));
                }
            }
            stm.setInt(parameters.size() + 1, offset);
            stm.setInt(parameters.size() + 2, limit);
            rs = stm.executeQuery();

            while (rs.next()) {
                Page page = new Page();
                page.setId(rs.getInt("Id"));
                page.setName(rs.getString("Name"));
                page.setUri(rs.getString("URI"));
                page.setDescription(rs.getString("Description"));
                page.setObjectSetting(settingDAO.getById(rs.getInt("ObjectSettingId")));
                page.setStatus(rs.getBoolean("Status"));
                pageList.add(page);
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            settingDAO.closeConnection();
        }
        return pageList;
    }

    /**
     * Retrieves a number of pages based on the specified range and filter
     * parameters.
     *
     * @param filterList a map containing filter parameters (key-value pairs) to
     * apply to the query
     * @return a number of Page objects matching the specified criteria, or 0 if
     * there is an exception occurred
     */
    public int numberOfRecords(List<Map<Object, String>> filterList) {
        String strSelect = "SELECT COUNT(*) FROM Pages";
        List<Object> parameters = new ArrayList<>();
        //  Prevent null pointer exception
        if (filterList == null) {
            filterList = new ArrayList<>();
        }
        //  Update SQL command based on the filter list
        if (!filterList.isEmpty()) {
            strSelect += " WHERE ";
            for (int i = 0; i < filterList.size(); i++) {
                strSelect += " ( ";
                int count = 0;
                Map<Object, String> filters = filterList.get(i);
                for (Map.Entry<Object, String> entry : filters.entrySet()) {
                    String value = entry.getValue();
                    strSelect += value + " =? ";
                    count++;
                    if (count < filters.size()) {
                        strSelect += " OR ";
                    }
                    parameters.add(entry.getKey());
                }
                strSelect += " ) ";
                if (i < filterList.size() - 1) {
                    strSelect += " AND ";
                }
            }
        }
        try {
            stm = connection.prepareStatement(strSelect);
            if (!parameters.isEmpty()) {
                for (int i = 0; i < parameters.size(); i++) {
                    stm.setObject(i + 1, parameters.get(i));
                }
            }
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    /**
     * Retrieves a list of pages accessed by role parameters.
     *
     * @param roles a list containing role parameters (role setting id) to apply
     * to the query
     * @return a list of Page objects or null if there's none
     */
    public List<Integer> pagesByRoles(List<Integer> roles) {
        String strSelect = "SELECT DISTINCT PageId FROM PageAccess";
        List<Object> parameters = new ArrayList<>();
        //  Prevent null pointer exception
        if (roles == null) {
            roles = new ArrayList<>();
        }
        //  Update SQL command based on the filter list
        if (!roles.isEmpty()) {
            strSelect += " WHERE ";
            int count = 0;
            for (Integer roleId : roles) {
                strSelect += " RoleSettingId =? ";
                count++;
                if (count < roles.size()) {
                    strSelect += " OR ";
                }
                parameters.add(roleId);
            }
        }
        try {
            stm = connection.prepareStatement(strSelect);
            if (!parameters.isEmpty()) {
                for (int i = 0; i < parameters.size(); i++) {
                    stm.setObject(i + 1, parameters.get(i));
                }
            }
            rs = stm.executeQuery();
            List<Integer> pageList = new ArrayList<>();
            while (rs.next()) {
                pageList.add(rs.getInt(1));
            }
            return pageList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Retrieves a list of pages accessed by role parameters.
     *
     * @param roleId to get the list of pages based on a single role
     * @return a list of Page objects or null if there's none
     */
    public List<Page> pagesByRole(int roleId) {
        PageDAO functionDAO = new PageDAO();
        List<Page> pageList = new ArrayList<>();
        String strSelect = "SELECT * FROM PageAccess WHERE (RoleSettingId=? OR RoleSettingId=1) AND IsAllowed=true";
        try {
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, roleId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Page page = functionDAO.getById(rs.getInt("PageId"));
                pageList.add(page);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        functionDAO.closeConnection();
        return pageList;
    }

    /**
     * Retrieves a list of roles that has access to specified function
     *
     * @param pageId id of the function to query
     * @return a list of role IDs or null if there's none
     */
    public List<Integer> rolesByPage(int pageId) {
        String strSelect = "SELECT RoleSettingId FROM PageAccess WHERE PageId=?";
        List<Integer> rolesList = new ArrayList<>();
        try {
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, pageId);
            rs = stm.executeQuery();
            while (rs.next()) {
                rolesList.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return rolesList;
    }

    public boolean updatePermission(int roleId, int pageId, boolean permission) {
        try {
            String query = "SELECT * FROM PageAccess WHERE RoleSettingId = ? AND PageId = ?";
            stm = connection.prepareStatement(query);
            stm.setInt(1, roleId);
            stm.setInt(2, pageId);
            rs = stm.executeQuery();

            if (rs.next()) {
                // Nếu có record tương ứng, cập nhật thuộc tính IsAllowed
                String updateQuery = "UPDATE PageAccess SET IsAllowed = ? WHERE RoleSettingId = ? AND PageId = ?";
                PreparedStatement updateStm = connection.prepareStatement(updateQuery);
                updateStm.setBoolean(1, permission);
                updateStm.setInt(2, roleId);
                updateStm.setInt(3, pageId);
                int updatedRows = updateStm.executeUpdate();
                updateStm.close();
                return updatedRows > 0;
            } else {
                // Nếu không có record tương ứng, thêm mới record
                String insertQuery = "INSERT INTO PageAccess (RoleSettingId, PageId, IsAllowed) VALUES (?, ?, ?)";
                PreparedStatement insertStm = connection.prepareStatement(insertQuery);
                insertStm.setInt(1, roleId);
                insertStm.setInt(2, pageId);
                insertStm.setBoolean(3, permission);
                int insertedRows = insertStm.executeUpdate();
                insertStm.close();
                return insertedRows > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
