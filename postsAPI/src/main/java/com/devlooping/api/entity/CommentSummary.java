package com.devlooping.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comment_summary_view")
public class CommentSummary {

    /*
 CREATE VIEW comment_summary_view AS
 SELECT 	c.id_comment as id_comment,
		c.comment_content as comment_content,
        c.post_state_id as post_state_id,
        c.created_at as created_at,
        c.deleted_at as deleted_at,
        u.id_user AS user_id,
        p.id_post AS post_id
FROM COMMENT c
JOIN User u ON c.USER_id_user = u.id_user
JOIN post p ON c.POST_id_post = p.id_post;

     */
    @Id
    @Column(name = "id_comment")
    private Long idComment;
    @Column(name = "comment_content")
    private String commentContent;
    @Column(name = "post_state_id")
    private Long postStateId;
    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "deleted_at")
    private String deletedAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "post_id")
    private Long postId;

    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Long getPostStateId() {
        return postStateId;
    }

    public void setPostStateId(Long postStateId) {
        this.postStateId = postStateId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public CommentSummary() {
    }

    public CommentSummary(Long idComment, String commentContent, Long postStateId, String createdAt, String deletedAt, User user, Long postId) {
        this.idComment = idComment;
        this.commentContent = commentContent;
        this.postStateId = postStateId;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.user = user;
        this.postId = postId;
    }

}
