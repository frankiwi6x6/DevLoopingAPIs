package com.devlooping.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

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

    @Column(name = "likes_count")
    private Long likesCount;

    @Column(name = "shares_count")
    private Long sharesCount;

    @Column(name = "comments_count")
    private Long commentsCount;

    public PostSummary() {
    }

    

    public PostSummary(Long postId, PostState postState, User user, String postContent, LocalDateTime createdAt,
            LocalDateTime updatedAt, LocalDateTime deletedAt, Long likesCount, Long sharesCount, Long commentsCount) {
        this.postId = postId;
        this.postState = postState;
        this.user = user;
        this.postContent = postContent;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.likesCount = likesCount;
        this.sharesCount = sharesCount;
        this.commentsCount = commentsCount;
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


    public Long getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Long likesCount) {
        this.likesCount = likesCount;
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



    public User getUser() {
        return user;
    }



    public void setUser(User user) {
        this.user = user;
    }

}
