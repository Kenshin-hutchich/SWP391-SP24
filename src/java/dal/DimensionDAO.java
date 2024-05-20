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
import model.Dimension;

/**
 *
 * @author KENSHIN
 */
public class DimensionDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    public Dimension getById(int dimensionId) {
        Dimension dimension = new Dimension();
        String strSelect = "SELECT * FROM Dimension WHERE Id=?";
        try {
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, dimensionId);
            rs = stm.executeQuery();
            if (rs.next()) {
                dimension.setId(rs.getInt("Id"));
                dimension.setName(rs.getString("Name"));
                dimension.setDescription(rs.getString("Description"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return dimension;
    }

    public List<Dimension> getAll() {
        List<Dimension> dimensionList = new ArrayList<>();
        String strSelect = "SELECT * FROM Dimension";
        try {
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Dimension dimension = new Dimension();
                dimension.setId(rs.getInt("Id"));
                dimension.setName(rs.getString("Name"));
                dimension.setDescription(rs.getString("Description"));
                dimensionList.add(dimension);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return dimensionList;
    }
}
