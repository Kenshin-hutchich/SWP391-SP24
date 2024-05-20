/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dell
 */
public class Slider {
    private int id;
    private String title;
    private String image;
    private String backlink;
    private boolean status;

    public Slider() {
    }

    public Slider( String title, String image, String backlink, boolean status) {
       
        this.title = title;
        this.image = image;
        this.backlink = backlink;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBacklink() {
        return backlink;
    }

    public void setBacklink(String backlink) {
        this.backlink = backlink;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "slider{" + "id=" + id + ", title=" + title + ", image=" + image + ", backlink=" + backlink + ", status=" + status + '}';
    }
    
    
}
