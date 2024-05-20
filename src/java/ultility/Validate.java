/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ultility;

import dal.DBContext;
import dal.UserDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Setting;
import model.User;

/**
 *
 * @author tudo7
 */
public class Validate extends DBContext {

    Setting s = new Setting();

    public boolean checkUserExisted(String email, String phone) throws SQLException {
        UserDAO u = new UserDAO();
        List<User> list = u.getAll();
        for (User user : list) {
            if (user.getEmail().equalsIgnoreCase(email) || user.getMobile().equalsIgnoreCase(phone)) {
                return true;
            }
        }
        return false;
    }

    public User checkExistedEmail(String email) {
        try {
            String sql = "SELECT * FROM user where email=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
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
                return a;

            }

        } catch (SQLException ex) {
            Logger.getLogger(model.User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User checkExistedPhone(String phone) {
        try {
            String sql = "SELECT * FROM user where mobile=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, phone);
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
                return a;

            }

        } catch (SQLException ex) {
            Logger.getLogger(model.User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        Validate v = new Validate();
        System.out.println(v.checkExistedEmail("tudo766@gmail.com"));
        System.out.println(v.checkExistedPhone("0559485699"));

    }
}
