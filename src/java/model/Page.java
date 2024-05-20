/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author KENSHIN
 */
public class Page {

    private int id;
    private String name;
    private String uri;
    private String description;
    private Setting objectSetting;
    private Boolean status;

    public Page() {

    }

    public Page(int id, String name, String uri, String description, Setting objectSetting, Boolean status) {
        this.id = id;
        this.name = name;
        this.uri = uri;
        this.description = description;
        this.objectSetting = objectSetting;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setObjectSetting(Setting objectSetting) {
        this.objectSetting = objectSetting;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }

    public String getDescription() {
        return description;
    }

    public Setting getObjectSetting() {
        return objectSetting;
    }

    public Boolean getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Page other = (Page) obj;
        return this.id == other.id;
    }

}
