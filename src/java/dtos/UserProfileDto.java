/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import model.User;


/**
 *
 * @author KENSHIN
 */
public class UserProfileDto {
    private int id;
    private String email;
    private String name;
    private String mobile;
    private boolean gender;
    private String avatar;

    public UserProfileDto() {
    }

    public UserProfileDto(int id, String email, String name, String mobile, boolean gender, String avatar) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.mobile = mobile;
        this.gender = gender;
        this.avatar = avatar;
    }
    
    public UserProfileDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.mobile = user.getMobile();
        this.gender = user.getGender();
        this.avatar = user.getAvatar();
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public boolean isGender() {
        return gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    
}
