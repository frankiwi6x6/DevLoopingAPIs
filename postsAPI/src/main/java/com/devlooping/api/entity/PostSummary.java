package com.devlooping.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "post_summary_view")
public class PostSummary {

    @Id
    @Column(name = "id_post")
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "id_post_state")
    private PostState postState;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "post_content")
    private String postContent;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "likes_users")
    private String likesUsers;

    @Column(name = "shares_count")
    private Long sharesCount;

    @Column(name = "comments_count")
    private Long commentsCount;

    @Column(name = "etiquetas")
    private String etiquetas;

    public String getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(String etiquetas) {
        this.etiquetas = etiquetas;
    }

    public PostSummary() {
    }

    public PostSummary(Long postId, PostState postState, User user, String postContent, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, String likesUsers, Long sharesCount, Long commentsCount, String etiquetas) {
        this.postId = postId;
        this.postState = postState;
        this.user = user;
        this.postContent = postContent;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.likesUsers = likesUsers;
        this.sharesCount = sharesCount;
        this.commentsCount = commentsCount;
        this.etiquetas = etiquetas;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public List<Long> getLikesUsers() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Long> likesList = new ArrayList<>();
        if (likesUsers != null && !likesUsers.trim().isEmpty()) {
            try {
                likesList = objectMapper.readValue(likesUsers, new TypeReference<List<Long>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return likesList;
    }

    public Long getSharesCount() {
        return sharesCount;
    }

    public void setSharesCount(Long sharesCount) {
        this.sharesCount = sharesCount;
    }

    public Long getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Long commentsCount) {
        this.commentsCount = commentsCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public PostState getPostState() {
        return postState;
    }

    public void setPostState(PostState postState) {
        this.postState = postState;
    }

}
