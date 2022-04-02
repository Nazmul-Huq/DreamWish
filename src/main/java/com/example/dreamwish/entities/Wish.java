package com.example.dreamwish.entities;

public class  Wish {

    private int id;
    private String title;
    private String description;
    private String image;
    private String status;
    private String expireDate;
    private int userId;

    public Wish() {
    }

    public Wish(String title, String description, String image, String status, String expireDate, int userId) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.status = status;
        this.expireDate = expireDate;
        this.userId = userId;
    }

    public Wish(int id, String title, String description, String image, String status, String expireDate, int userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.status = status;
        this.expireDate = expireDate;
        this.userId = userId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Wish{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", status='" + status + '\'' +
                ", expireDate='" + expireDate + '\'' +
                ", userId=" + userId +
                '}';
    }
}
