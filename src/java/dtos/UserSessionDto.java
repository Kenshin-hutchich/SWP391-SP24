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
public class UserSessionDto {

    private int id;
    private String name;
    private String avatar;

    public UserSessionDto() {
        this.id = 0;
    }

    public UserSessionDto(int id, String name, String avatar) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
    }

    public UserSessionDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.avatar = user.getAvatar();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
