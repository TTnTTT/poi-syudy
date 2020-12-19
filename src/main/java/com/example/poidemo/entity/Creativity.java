package com.example.poidemo.entity;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author night bird
 * @version 1.0
 * @date 2020/12/18 16:10
 */
public class Creativity {
    private Integer id;
    private Integer userId;
    private String name;
    private String description;
    private Timestamp crtTime;
    private Timestamp modTime;
    private List<CreativityPic> creativityPics;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Timestamp crtTime) {
        this.crtTime = crtTime;
    }

    public Timestamp getModTime() {
        return modTime;
    }

    public void setModTime(Timestamp modTime) {
        this.modTime = modTime;
    }

    public List<CreativityPic> getCreativityPics() {
        return creativityPics;
    }

    public void setCreativityPics(List<CreativityPic> creativityPics) {
        this.creativityPics = creativityPics;
    }

}
