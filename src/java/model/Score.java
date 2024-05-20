/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Time;

/**
 *
 * @author win
 */
public class Score {

    private int scoreId;
    private int quizId;
    private double score;
    private int userId;
    private int time;

    private Quiz quiz;
    private User user;

    public Score() {
    }

    public Score(int scoreId, int quizId, double score, int userId, int time) {
        this.scoreId = scoreId;
        this.quizId = quizId;
        this.score = score;
        this.userId = userId;
        this.time = time;
    }

    public Score(int scoreId, int quizId, double score, int userId, int time, Quiz quiz, User user) {
        this.scoreId = scoreId;
        this.quizId = quizId;
        this.score = score;
        this.userId = userId;
        this.time = time;
        this.quiz = quiz;
        this.user = user;
    }

    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Score{" + "scoreId=" + scoreId + ", quizId=" + quizId + ", score=" + score + ", userId=" + userId + ", time=" + time + ", quiz=" + quiz + ", user=" + user + '}';
    }
}
