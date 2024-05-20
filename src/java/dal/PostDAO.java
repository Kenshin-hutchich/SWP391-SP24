/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import com.google.api.client.util.DateTime;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Post;

/**
 *
 * @author KENSHIN
 */
public class PostDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    // Get number of Posts
    public int numberOfPosts() {
        try {
            String strSelect = "SELECT COUNT(*) FROM Post";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    // Get all Posts
    public List<Post> getAll() {
        List<Post> postList = new ArrayList<>();
        UserDAO userDAO = new UserDAO();
        try {
            String strSelect = "SELECT * FROM Post ORDER BY Id DESC";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("Id"));
                post.setAuthor(userDAO.getById(rs.getInt("AuthorId")));
                post.setThumbnail(rs.getString("Thumbnail"));
                post.setTitle(rs.getString("Title"));
                post.setBriefInfo(rs.getString("BriefInfo"));
                post.setDescription(rs.getString("Description"));
                post.setFeatured(rs.getBoolean("Featured"));
                post.setCreatedAt(rs.getTimestamp("CreatedAt"));
                post.setStatus(rs.getBoolean("Status"));
                postList.add(post);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            userDAO.closeConnection();
        }
        return postList;
    }

    // Get Post by ID
    public Post getById(int postId) {
        UserDAO userDAO = new UserDAO();
        Post post = new Post();
        try {
            String strSelect = "SELECT * FROM Post WHERE Id=?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, postId);
            rs = stm.executeQuery();
            if (rs.next()) {
                post.setId(rs.getInt("Id"));
                post.setAuthor(userDAO.getById(rs.getInt("AuthorId")));
                post.setThumbnail(rs.getString("Thumbnail"));
                post.setTitle(rs.getString("Title"));
                post.setBriefInfo(rs.getString("BriefInfo"));
                post.setDescription(rs.getString("Description"));
                post.setFeatured(rs.getBoolean("Featured"));
                post.setCreatedAt(rs.getTimestamp("CreatedAt"));
                post.setStatus(rs.getBoolean("Status"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            userDAO.closeConnection();
        }
        return post;
    }

    // Create a new Post
    // Return true if Post is created successfully, otherwise return false
    public boolean insert(Post post) {
        try {
            String strInsert = "INSERT INTO Post (AuthorId, Thumbnail, Title, BriefInfo, Description, Featured, CreatedAt, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stm = connection.prepareStatement(strInsert);
            stm.setInt(1, post.getAuthor().getId());
            stm.setString(2, post.getThumbnail());
            stm.setString(3, post.getTitle());
            stm.setString(4, post.getBriefInfo());
            stm.setString(5, post.getDescription());
            stm.setBoolean(6, post.isFeatured());
            stm.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            stm.setBoolean(8, post.getStatus());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // Update an existing Post
    // Return true if Post is updated successfully, otherwise return false
    public boolean update(Post post) {
        try {
            String strUpdate = "UPDATE Post SET AuthorId=?, Thumbnail=?, Title=?, BriefInfo=?, Description=?, Featured=?, CreatedAt=?, Status=? WHERE Id=?";
            stm = connection.prepareStatement(strUpdate);
            stm.setInt(1, post.getAuthor().getId());
            stm.setString(2, post.getThumbnail());
            stm.setString(3, post.getTitle());
            stm.setString(4, post.getBriefInfo());
            stm.setString(5, post.getDescription());
            stm.setBoolean(6, post.isFeatured());
            stm.setBoolean(7, post.getStatus());
            stm.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            stm.setInt(9, post.getId());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // Delete an existing Post by ID
    // Return true if Post is deleted successfully, otherwise return false
    public boolean delete(int postId) {
        try {
            String strDelete = "DELETE FROM Post WHERE Id=?";
            stm = connection.prepareStatement(strDelete);
            stm.setInt(1, postId);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // Get paginated list of Posts
    public List<Post> getListByPage(List<Post> list,
            int start, int end) {
        List<Post> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    // Get paginated list of Posts
    public List<Post> getListByPage(int start, int end) {
        UserDAO userDAO = new UserDAO();
        List<Post> postList = new ArrayList<>();
        try {
            String strSelect = "SELECT * FROM Post ORDER BY Id DESC LIMIT ? OFFSET ?";
            stm = connection.prepareStatement(strSelect);
            int limit = end - start;
            stm.setInt(1, limit);
            stm.setInt(2, start);
            rs = stm.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("Id"));
                post.setAuthor(userDAO.getById(rs.getInt("AuthorId")));
                post.setThumbnail(rs.getString("Thumbnail"));
                post.setTitle(rs.getString("Title"));
                post.setBriefInfo(rs.getString("BriefInfo"));
                post.setDescription(rs.getString("Description"));
                post.setFeatured(rs.getBoolean("Featured"));
                post.setCreatedAt(rs.getTimestamp("CreatedAt"));
                post.setStatus(rs.getBoolean("Status"));
                postList.add(post);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            userDAO.closeConnection();
        }
        return postList;
    }
}
