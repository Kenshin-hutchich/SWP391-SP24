/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tudo7
 */
public class OldUser {

    int id;
    String email;
    String password;
    String name;
    String mobile;
    boolean gender;
    boolean status;
    String avatar;
    Setting role_id;

    public OldUser() {
    }

    public OldUser(int id, String email, String password, String name, String mobile, boolean gender, boolean status, String avatar, Setting role_id) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.mobile = mobile;
        this.gender = gender;
        this.status = status;
        this.avatar = avatar;
        this.role_id = role_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Setting getRole_id() {
        return role_id;
    }

    public void setRole_id(Setting role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "user{" + "id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", mobile=" + mobile + ", gender=" + gender + ", status=" + status + ", avatar=" + avatar + ", role_id=" + role_id + '}';
    }

    

}
