/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.sql.Timestamp;
import model.Setting;
import model.User;

/**
 *
 * @author KENSHIN
 */
public class UserUpdateDto {
    private int id;
    private String name;
    private boolean gender;
    private String mobile;
    private Timestamp updatedAt;
    private Boolean status;
    private Setting roleSetting;
    
    public UserUpdateDto() {
    }

    public UserUpdateDto(int id, String name, boolean gender, String mobile, Timestamp updatedAt, Boolean status, Setting roleSetting) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.mobile = mobile;
        this.updatedAt = updatedAt;
        this.status = status;
        this.roleSetting = roleSetting;
    }

    public UserUpdateDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.gender = user.getGender();
        this.mobile = user.getMobile();
        this.updatedAt = user.getUpdatedAt();
        this.status = user.getStatus();
        this.roleSetting = user.getRoleSetting();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getGender() {
        return gender;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public Boolean getStatus() {
        return status;
    }

    public Setting getRoleSetting() {
        return roleSetting;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setRoleSetting(Setting roleSetting) {
        this.roleSetting = roleSetting;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
}
