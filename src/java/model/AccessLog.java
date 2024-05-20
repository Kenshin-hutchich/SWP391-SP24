/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author KENSHIN
 */
public class AccessLog {
    private int id;
    private LocalDateTime accessTime;
    private String ipAddress;
    private Page pageAccessed;

    public AccessLog() {
    }

    public AccessLog(int id, LocalDateTime accessTime, String ipAddress, Page pageAccessed) {
        this.id = id;
        this.accessTime = accessTime;
        this.ipAddress = ipAddress;
        this.pageAccessed = pageAccessed;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getAccessTime() {
        return accessTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Page getPageAccessed() {
        return pageAccessed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccessTime(LocalDateTime accessTime) {
        this.accessTime = accessTime;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setPageAccessed(Page pageAccessed) {
        this.pageAccessed = pageAccessed;
    }
}
