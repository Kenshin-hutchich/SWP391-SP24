/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Comment;

/**
 *
 * @author KENSHIN
 */
public class CommentDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    // Create a new Comment
    public boolean create(Comment comment) {
        try {
            String strInsert = "INSERT INTO Comment (PostId, AuthorId, Content) VALUES (?, ?, ?)";
            stm = connection.prepareStatement(strInsert, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, comment.getPost().getId());
            stm.setInt(2, comment.getAuthor().getId());
            stm.setString(3, comment.getContent());
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    // Get Comments by Post ID
    public List<Comment> getByPostId(int postId) {
        System.out.println("USE COMMENTDAO");
        UserDAO userDAO = new UserDAO();
        PostDAO postDAO = new PostDAO();
        
        List<Comment> commentList = new ArrayList<>();
        try {
            String strSelect = "SELECT * FROM Comment WHERE PostId=?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, postId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("Id"));
                comment.setPost(postDAO.getById(rs.getInt("PostId")));
                comment.setAuthor(userDAO.getById(rs.getInt("AuthorId")));
                comment.setCreatedAt(rs.getTimestamp("CreatedAt"));
                comment.setContent(rs.getString("Content"));
                commentList.add(comment);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            userDAO.closeConnection();
            postDAO.closeConnection();
        }
        
        return commentList;
    }

    // Update an existing Comment's content
    public boolean update(Comment comment) {
        try {
            String strUpdate = "UPDATE Comment SET Content=? WHERE Id=?";
            stm = connection.prepareStatement(strUpdate);
            stm.setString(1, comment.getContent());
            stm.setInt(2, comment.getId());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    // Delete an existing Comment by ID
    public boolean delete(int commentId) {
        try {
            String strDelete = "DELETE FROM Comment WHERE Id=?";
            stm = connection.prepareStatement(strDelete);
            stm.setInt(1, commentId);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
