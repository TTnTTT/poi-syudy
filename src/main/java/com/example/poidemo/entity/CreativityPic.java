package com.example.poidemo.entity;

/** 创意图片
 * @author night bird
 * @version 1.0
 * @date 2020/12/18 16:16
 */
public class CreativityPic {
    private Integer id;
    private String url;
    private String title;
    private String description;
    private String orders;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }
}
