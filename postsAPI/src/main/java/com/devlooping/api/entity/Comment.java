package com.devlooping.api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private Long id;
    @Column(name = "comment_content")
    private String content;
    @Column(name = "post_state_id")
    private Long postStateId;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    @Column(name = "USER_id_user")
    private Long userId;
    @Column(name = "POST_id_post")
    private Long postId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPostStateId() {
        return postStateId;
    }

    public void setPostStateId(Long postStateId) {
        this.postStateId = postStateId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Comment() {
    }

    public Comment(Long id, String content, Long postStateId, LocalDateTime createdAt, LocalDateTime deletedAt, Long userId, Long postId) {
        this.id = id;
        this.content = content;
        this.postStateId = postStateId;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.userId = userId;
        this.postId = postId;
    }
    

}
