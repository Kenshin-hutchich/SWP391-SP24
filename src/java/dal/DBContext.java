package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

    protected Connection connection;
    static int connectionCount = 0;
    static int totalConnection = 0;

    public DBContext() {
        connectionCount++;
        totalConnection++;
        System.out.println("Total:" + totalConnection + " Connection number: " + connectionCount);
        try {
            String user = "root";
            String pass = "123456789";
            String url = "jdbc:mysql://127.0.0.1:3306/swp391_lms";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                connectionCount--;
                System.out.println("Connection number: " + connectionCount);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
