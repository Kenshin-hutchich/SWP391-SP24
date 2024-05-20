/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filter;

import dal.AccessLogDAO;
import dal.PageDAO;
import dal.SettingDAO;
import dal.UserDAO;
import java.time.LocalDateTime;
import java.util.List;
import model.AccessLog;
import model.Page;
import model.Setting;
import org.checkerframework.checker.units.qual.A;

/**
 *
 * @author KENSHIN
 */
public class AccessControl {

    List<Page> privatePages;
    List<Page> publicPages;

    public AccessControl() {
        PageDAO pageDAO = new PageDAO();
        SettingDAO settingDAO = new SettingDAO();
        privatePages = pageDAO.getAll();
        Setting guestRole = settingDAO.getByNameAndValue("Role", "Guest");
        publicPages = pageDAO.pagesByRole(guestRole.getId());
        privatePages.removeAll(publicPages);
        pageDAO.closeConnection();
        settingDAO.closeConnection();
    }

    /*
        Explaination: 
        1. Check if the request URI matches any URI in each role-specified URIs collection.
        2. If it matches, check if the user has the corresponding role.
        3. Return true if user has access to that URI, otherwise return false.
        Exception: if user is trying to access an unknown URI, this function still return true.
     */
    boolean hasAccess(String requestURI, int userId, String userIp) {
        for (Page p : publicPages) {
            if (requestURI.equals(p.getUri())) {
                RecordAccessLog(userIp, p);
                return true;
            }
        }
        for (Page p : privatePages) {
            if (requestURI.equals(p.getUri())) {
                if (userId == 0) {
                    return false;
                }
                PageDAO pageDAO = new PageDAO();
                UserDAO userDAO = new UserDAO();
                //  Get list of role IDs that have access to this page
                List<Integer> roleIds = pageDAO.rolesByPage(p.getId());
                int userRoleId = userDAO.getById(userId).getRoleSetting().getId();
                pageDAO.closeConnection();
                userDAO.closeConnection();
                //  If user role Id is one of the allowed role Ids, return true.
                if (roleIds.contains(userRoleId)){
                    RecordAccessLog(userIp, p);
                    return true;
                }
                //  Otherwise, see if user role Id has higher priority than all the allowed role
                SettingDAO settingDAO = new SettingDAO();
                for (int roleId : roleIds) {
                    Setting role = settingDAO.getById(roleId);
                    Setting userRole = settingDAO.getById(userRoleId);
                    if (role.getOrder() <= userRole.getOrder()) {
                        settingDAO.closeConnection();
                        return false;
                    }
                }
                //  If user role has higher priority than all of the required role Ids, return true
                settingDAO.closeConnection();
                return true;
            }
        }
        return true;
    }

    private void RecordAccessLog(String userIp, Page page) {
        AccessLogDAO accessLogDao = new AccessLogDAO();
        PageDAO pageDAO = new PageDAO();

        AccessLog accessLog = new AccessLog();
        accessLog.setIpAddress(userIp);
        accessLog.setPageAccessed(pageDAO.getById(page.getId()));
        accessLog.setAccessTime(LocalDateTime.now());
        accessLogDao.insert(accessLog);

        accessLogDao.closeConnection();
        pageDAO.closeConnection();
    }
}
