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
public class User {

    private int id;
    private String email;
    private String hash;
    private String name;
    private String mobile;
    private boolean gender;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Boolean status;
    private String avatar;
    private Setting roleSetting;

    public User() {

    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getHash() {
        return hash;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public boolean getGender() {
        return gender;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getAvatar() {
        return avatar;
    }

    public Setting getRoleSetting() {
        return roleSetting;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setRoleSetting(Setting roleSetting) {
        this.roleSetting = roleSetting;
    }

    public boolean isGender() {
        return gender;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", hash=" + hash + ", name=" + name + ", mobile=" + mobile + ", gender=" + gender + ", status=" + status + ", avatar=" + avatar + ", roleSetting=" + roleSetting + '}';
    }

}
