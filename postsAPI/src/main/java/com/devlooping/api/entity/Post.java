package com.devlooping.api.entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_post")
    private int id;
    @Column (name = "desc_post")
    private String descPost;
    @Column (name = "img_post")
    private byte[] imgPost;
    @Column (name = "like_post")
    private int likePost;
    @Column (name = "created_at")
    private Date createdAt;
    @Column (name = "updated_at")
    private Date updatedAt;
    @Column (name = "USER_id_user")
    private int userId;

    // Constructor
    public Post(int id, String descPost, byte[] imgPost, int likePost, Date createdAt, Date updatedAt, int userId) {
    
        this.id = id;
        this.descPost = descPost;
        this.imgPost = imgPost;
        this.likePost = likePost;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescPost() {
        return descPost;
    }

    public void setDescPost(String descPost) {
        if (descPost == null || descPost.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (descPost.length() > 255) {
            throw new IllegalArgumentException("Description is too long");
        }
        this.descPost = descPost;
    }

    public byte[] getImgPost() {
        return imgPost;
    }

    public void setImgPost(byte[] imgPost) {
        this.imgPost = imgPost;
    }

    public int getLikePost() {
        return likePost;
    }

    public void setLikePost(int likePost) {
        this.likePost = likePost;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}