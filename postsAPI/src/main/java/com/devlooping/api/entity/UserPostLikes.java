package com.devlooping.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER_POST_LIKES")
public class UserPostLikes {
    @Id
    
    int id;
    @Column(name = "USER_id_user")    
    private int idUser;
    @Column(name = "POST_id_post")
    private int idPost;

    public UserPostLikes() {
    }

    public UserPostLikes(int idUser, int idPost) {
        this.idUser = idUser;
        this.idPost = idPost;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    
}
