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
public class UserListDto {

    private int id;
    private String email;
    private String name;
    private boolean gender;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Boolean status;
    private Setting roleSetting;

    public UserListDto() {
    }

    public UserListDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.gender = user.getGender();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.status = user.getStatus();
        this.roleSetting = user.getRoleSetting();
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

    public boolean isGender() {
        return gender;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
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

}
