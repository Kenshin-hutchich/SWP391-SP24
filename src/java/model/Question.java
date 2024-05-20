/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author win
 */
public class Question {

    private int id;
    private String content;
    private int level;
    private String explaination;
    private int status;

    private int quizId;
    private Quiz quiz;

    public Question() {
    }

    public Question(int id, String content, int level, String explaination, int status) {
        this.id = id;
        this.content = content;
        this.level = level;
        this.explaination = explaination;
        this.status = status;
    }

    public Question(int id, String content, int level, String explaination, int status, int quizId, Quiz quiz) {
        this.id = id;
        this.content = content;
        this.level = level;
        this.explaination = explaination;
        this.status = status;
        this.quizId = quizId;
        this.quiz = quiz;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getExplaination() {
        return explaination;
    }

    public void setExplaination(String explaination) {
        this.explaination = explaination;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", content=" + content + ", level=" + level + ", explaination=" + explaination + ", status=" + status + ", quizId=" + quizId + ", quiz=" + quiz + '}';
    }

}
