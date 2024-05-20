/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author KENSHIN
 */
public class Lesson {

    private int id;
    private Course course;
    private int orderNumber;
    private String title;
    private String videoSrc;
    private String content;

    public Lesson() {
    }

    public Lesson(int id, Course course, int orderNumber, String title, String videoSrc, String content) {
        this.id = id;
        this.course = course;
        this.orderNumber = orderNumber;
        this.title = title;
        this.videoSrc = videoSrc;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getVideoSrc() {
        return videoSrc;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVideoSrc(String videoSrc) {
        this.videoSrc = videoSrc;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
