/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author KENSHIN
 */
public class Course {

    private int id;
    private String subjectCode;
    private String title;
    private String description;
    private String thumbnail;
    private int numberOfParticipants;
    private Dimension dimension;
    private User owner;
    private Boolean status;
    private boolean featured;

    public Course() {
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public int getId() {
        return id;
    }

    public String getSubjectCode() {
        return subjectCode;
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

    public Dimension getDimension() {
        return dimension;
    }

    public User getOwner() {
        return owner;
    }

    public Boolean getStatus() {
        return status;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
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

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }
}
