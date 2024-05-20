/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author KENSHIN
 */
public class Log {

    private int id;
    private User actor;
    private Setting actionSetting;
    private Setting targetTypeSetting;
    private int targetId;
    private Timestamp time;

    // Constructors
    public Log() {

    }

    public Log(User actor, Setting actionSetting, Setting targetTypeSetting, int targetId, Timestamp time) {
        this.actor = actor;
        this.actionSetting = actionSetting;
        this.targetTypeSetting = targetTypeSetting;
        this.targetId = targetId;
        this.time = time;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getActor() {
        return actor;
    }

    public void setActor(User actor) {
        this.actor = actor;
    }

    public Setting getActionSetting() {
        return actionSetting;
    }

    public void setActionSetting(Setting actionSetting) {
        this.actionSetting = actionSetting;
    }

    public Setting getTargetTypeSetting() {
        return targetTypeSetting;
    }

    public void setTargetTypeSetting(Setting targetTypeSetting) {
        this.targetTypeSetting = targetTypeSetting;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
