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
import model.Slider;

/**
 *
 * @author Dell
 */
public class SlidersDAO extends DBContext {

    PreparedStatement ps = null;//lenh db
    ResultSet rs = null;//tra ve

    public List<Slider> getAllSliders() {
        List<Slider> list = new ArrayList<>();

        String query = "SELECT * FROM swp391_lms.Slider;";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Slider s = new Slider();

                s.setId(rs.getInt(1));
                s.setTitle(rs.getString(2));
                s.setImage(rs.getString(3));
                s.setBacklink(rs.getString(4));
                s.setStatus(rs.getBoolean(5));
                list.add(s);

            }

        } catch (Exception e) {
        }

        return list;

    }

    public List<Slider> getListByPage(List<Slider> list,
            int start, int end) {
        List<Slider> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public void insert(Slider Slider) {
        String query
                = "INSERT INTO `swp391_lms`.`Slider` ( `Title`, `Image`, `Backlink`, `Status`) "
                + "VALUES ( ?, ?, ?, ?);";

        try {
            ps = connection.prepareStatement(query);

            ps.setString(1, Slider.getTitle());
            ps.setString(2, Slider.getImage());
            ps.setString(3, Slider.getBacklink());

            int statusValue = Slider.isStatus() ? 1 : 0;
            ps.setInt(4, statusValue);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String query
                = "delete  FROM swp391_lms.Slider where id=?;";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Slider getSliderById(int id) {
        String query = "select * FROM swp391_lms.Slider where id=?;";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                //dung if khi chi lay ra mot phan tu
                Slider sl = new Slider();

                sl.setId(rs.getInt(1));
                sl.setTitle(rs.getString(2));
                sl.setImage(rs.getString(3));
                sl.setBacklink(rs.getString(4));
                sl.setStatus(rs.getBoolean(5));

                return sl;
            }

        } catch (SQLException e) {

        }

        return null;
    }

    public void update(Slider c) {
        String query = "  UPDATE `swp391_lms`.`Slider` SET   `Title` =? ,`Image` =? , `Backlink` = ?,  `Status` = ? WHERE (`Id` = ?);";

        try {
            ps = connection.prepareStatement(query);

            ps.setString(1, c.getTitle());
            ps.setString(2, c.getImage());
            ps.setString(3, c.getBacklink());

            int statusValue = c.isStatus() ? 1 : 0;
            ps.setInt(4, statusValue);

            ps.setInt(5, c.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
        }

    }

    public List<Slider> search(String title, String backlink, int status) {

        List<Slider> list = new ArrayList<>();
        String query = "SELECT * FROM swp391_lms.Slider where 1=1 ";
        if (title != null && !title.equals("")) {
            query += "and `Title` like '%" + title + "%'";
        }
        if (backlink != null && !backlink.equals("")) {
            query += " and `Backlink` like '%" + backlink + "%'";
        }
        if (status < 2) {
            query += " and status = " + status;
        }
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Slider sl = new Slider();
                sl.setId(rs.getInt("id"));
                sl.setTitle(rs.getString("title"));
                sl.setImage(rs.getString("image"));
                sl.setBacklink(rs.getString("backlink"));
                sl.setStatus(rs.getBoolean("status"));
                list.add(sl);

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return list;
    }

    public static void main(String[] args) {
        SlidersDAO dao = new SlidersDAO();
        Slider sl = new Slider("ACC", "pic3.jpg", "Demo_backlink2", true);

        List<Slider> list = dao.getAllSliders();
        List<Slider> list1 = dao.search("e", "e", 0);
        for (Slider e : list) {
            System.out.println(e);
        }
        //dao.insert(sl);
        //System.out.println(dao.getSliderById(1));

    }

}
