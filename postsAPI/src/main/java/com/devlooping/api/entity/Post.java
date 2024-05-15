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
    @Column(name = "id_post")
    private int id;

    @Column(name = "post_content")
    private String content;

    @Column(name = "post_state_id")
    private int state;

    @Column(name = "uploaded_at")
    private Date uploadedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @Column(name = "USER_id_user")
    private int userIdUser;

    public Post() {
    }

    public Post(String content, int state, Date uploadedAt, Date deletedAt, int userIdUser) {
        this.content = content;
        this.state = state;
        this.uploadedAt = uploadedAt;
        this.deletedAt = deletedAt;
        this.userIdUser = userIdUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Date uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public int getUserIdUser() {
        return userIdUser;
    }

    public void setUserIdUser(int userIdUser) {
        this.userIdUser = userIdUser;
    }

    @Override
    public String toString() {
        return "Post [content=" + content + ", deletedAt=" + deletedAt + ", id=" + id + ", state=" + state
                + ", uploadedAt=" + uploadedAt + ", userIdUser=" + userIdUser + "]";
    }

}
