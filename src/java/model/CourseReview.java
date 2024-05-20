/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author KENSHIN
 */
public class CourseReview {
    private User user;
    private Course course;
    private int rating;

    public CourseReview() {
    }

    public CourseReview(User user, Course course, int rating) {
        this.user = user;
        this.course = course;
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public Course getCourse() {
        return course;
    }

    public int getRating() {
        return rating;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
