/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.sql.Timestamp;
import model.User;

/**
 *
 * @author KENSHIN
 */
public class PostListDto {
    private int id;
    private User author;
    private String thumbnail;
    private String title;
    private String briefInfo;
    private boolean featured;
    private Timestamp createdAt;
    private int numberOfComment;

    public PostListDto() {
    }

    public int getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getBriefInfo() {
        return briefInfo;
    }

    public boolean isFeatured() {
        return featured;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public int getNumberOfComment() {
        return numberOfComment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setNumberOfComment(int numberOfComment) {
        this.numberOfComment = numberOfComment;
    }
    
}
