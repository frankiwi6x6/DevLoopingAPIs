package com.devlooping.api.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.devlooping.api.entity.Comment;
import com.devlooping.api.entity.Post;
import com.devlooping.api.entity.PostSummary;
import com.devlooping.api.exception.ForbiddenException;
import com.devlooping.api.exception.PostNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class PostDAO {

    @PersistenceContext
    private EntityManager entityManager;

    Long PUBLICADO = 1L;
    Long OCULTO = 2L;
    Long ELIMINADO = 3L;

    public List<PostSummary> getAllPostSummaries() {
        return entityManager.createQuery("SELECT ps FROM PostSummary ps", PostSummary.class)
                .getResultList();
    }


    

    public List<PostSummary> getPostSummariesByUser(Long idUser) {
        return entityManager.createQuery("SELECT ps FROM PostSummary ps WHERE ps.user.id = :idUser", PostSummary.class)
                .setParameter("idUser", idUser)
                .getResultList();
    }

    public List<PostSummary> getAllPublishedPostSummaries() {
        return entityManager.createQuery("SELECT ps FROM PostSummary ps WHERE ps.postState.id = :PUBLICADO", PostSummary.class)
                .setParameter("PUBLICADO", PUBLICADO)
                .getResultList();
    }

    public List<PostSummary> getPublishedPostSummariesByUser(Long idUser) {
        return entityManager.createQuery("SELECT ps FROM PostSummary ps WHERE ps.user.id = :idUser AND ps.postState.id = :PUBLICADO", PostSummary.class)
                .setParameter("idUser", idUser)
                .setParameter("PUBLICADO", PUBLICADO)
                .getResultList();
    }

    public PostSummary getPostSummaryById(Long idPost) {
        PostSummary postSummary = entityManager.find(PostSummary.class, idPost);
        if (postSummary == null) {
            throw new PostNotFoundException("Post not found with id: " + idPost);
        }
        return postSummary;
    }

    @Transactional
    public Post savePost(Post post) {
        post.setId(0L);
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(null);
        post.setDeletedAt(null);

        return entityManager.merge(post);
    }

    @Transactional
    public void updatePost(Long idPost, Long idUser, String postContent) {
        Post post = entityManager.find(Post.class, idPost);
        if (post == null) {
            throw new PostNotFoundException("Post not found with id: " + idPost);
        }
        if (!post.getUserId().equals(idUser)) {
            throw new ForbiddenException("User not allowed to update this post");
        }
        post.setPostContent(postContent);
        post.setUpdatedAt(LocalDateTime.now());
        entityManager.merge(post);
    }

    @Transactional
    public void deletePost(Long idPost) {
        Post post = entityManager.find(Post.class, idPost);
        if (post == null) {
            throw new PostNotFoundException("Post not found with id: " + idPost);
        }
        post.setDeletedAt(LocalDateTime.now());
        post.setPostStateId(ELIMINADO);
        entityManager.merge(post);
    }

    @Transactional
    public void hidePost(Long idPost) {
        Post post = entityManager.find(Post.class, idPost);
        if (post == null) {
            throw new PostNotFoundException("Post not found with id: " + idPost);
        }
        post.setPostStateId(OCULTO);
        entityManager.merge(post);
    }

    @Transactional
    public void publishPost(Long idPost) {
        Post post = entityManager.find(Post.class, idPost);
        if (post == null) {
            throw new PostNotFoundException("Post not found with id: " + idPost);
        }
        post.setPostStateId(PUBLICADO);
        entityManager.merge(post);
    }

    @Transactional
    public void saveComment(Comment comment) {
        comment.setId(0L);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setDeletedAt(null);

        entityManager.merge(comment);
    }

    public List<Comment> getCommentsByPost(Long idPost) {
        return entityManager.createQuery("SELECT c FROM Comment c WHERE c.postId = :idPost", Comment.class)
                .setParameter("idPost", idPost)
                .getResultList();
    }
    @Transactional
    public void deleteComment(Long idComment) {
        Comment comment = entityManager.find(Comment.class, idComment);
        if (comment == null) {
            throw new PostNotFoundException("Comment not found with id: " + idComment);
        }
        comment.setDeletedAt(LocalDateTime.now());
        entityManager.merge(comment);
    }
    @Transactional
    public void updateComment(Long idComment, String commentContent) {
        Comment comment = entityManager.find(Comment.class, idComment);
        if (comment == null) {
            throw new PostNotFoundException("Comment not found with id: " + idComment);
        }
        comment.setContent(commentContent);
        entityManager.merge(comment);
    }

    public Comment getCommentById(Long idComment) {
        Comment comment = entityManager.find(Comment.class, idComment);
        if (comment == null) {
            throw new PostNotFoundException("Comment not found with id: " + idComment);
        }
        return comment;
    }   






}
