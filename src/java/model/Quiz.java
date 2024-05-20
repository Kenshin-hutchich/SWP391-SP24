/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author win
 */
public class Quiz {

    private int id;
    private String title;
    private String content;
    private int status;
    private int courseId;
    private int time;

    private Course course;

    public Quiz() {
    }

    public Quiz(int id, String title, String content, int status, int courseId, int time, Course course) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.courseId = courseId;
        this.time = time;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Quiz{" + "id=" + id + ", title=" + title + ", content=" + content + ", status=" + status + ", courseId=" + courseId + ", time=" + time + ", course=" + course + '}';
    }

}
