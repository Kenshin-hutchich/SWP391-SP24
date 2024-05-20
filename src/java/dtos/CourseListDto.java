/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import model.Dimension;
import model.User;

/**
 *
 * @author KENSHIN
 */
public class CourseListDto {
    private int id;
    private String code;
    private User owner;
    private String title;
    private String description;
    private String thumbnail;
    private int numberOfParticipants;
    private int currentParticipants;
    private int numberOfRating;
    private double rating;
    private Dimension dimension;
    private boolean status;
    private boolean featured;

    public CourseListDto() {
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public boolean isStatus() {
        return status;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public int getCurrentParticipants() {
        return currentParticipants;
    }

    public int getNumberOfRating() {
        return numberOfRating;
    }

    public double getRating() {
        return rating;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public void setCurrentParticipants(int currentParticipants) {
        this.currentParticipants = currentParticipants;
    }

    public void setNumberOfRating(int numberOfRating) {
        this.numberOfRating = numberOfRating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }
    
    
}
