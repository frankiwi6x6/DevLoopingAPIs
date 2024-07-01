package com.devlooping.api.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.devlooping.api.entity.Comment;
import com.devlooping.api.entity.CommentSummary;
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

    Long PRIVADO = 1L;
    Long PUBLICO = 2L;
    Long DESHABILITADO = 3L;

    String USER_API_URL = "http://localhost:8080/api/users/";

    // Summaries

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
        return entityManager.createQuery("SELECT ps FROM PostSummary ps WHERE ps.postState.id = :PUBLICO", PostSummary.class)
                .setParameter("PUBLICO", PUBLICO)
                .getResultList();
    }

    public List<PostSummary> getPublishedPostSummariesByUser(Long idUser) {
        return entityManager.createQuery("SELECT ps FROM PostSummary ps WHERE ps.user.id = :idUser AND ps.postState.id = :PUBLICO", PostSummary.class)
                .setParameter("idUser", idUser)
                .setParameter("PUBLICO", PUBLICO)
                .getResultList();
    }

    public PostSummary getPostSummaryById(Long idPost) {
        PostSummary postSummary = entityManager.find(PostSummary.class, idPost);
        if (postSummary == null) {
            throw new PostNotFoundException("Post not found with id: " + idPost);
        }
        return postSummary;
    }

    // Posts
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
    public void deletePost(Long idPost, Long idUser) {

        

        // Obtenemos el post
        int ADMIN = 1;
        int MODERADOR = 2;
        Post post = entityManager.find(Post.class, idPost);
        if (post == null) {
            throw new PostNotFoundException("Post not found with id: " + idPost);
        }



        
        // Verificar si el usuario es administrador o moderador
        List<?> isModerator = entityManager.createNativeQuery("SELECT * FROM USER WHERE id_user = :idUser AND user_type_id = :ADMIN OR user_type_id = :MODERADOR")
                .setParameter("idUser", idUser)
                .setParameter("ADMIN", ADMIN)
                .setParameter("MODERADOR", MODERADOR)
                .getResultList();
        
        
        // Si el usuario es dueño del post, entonces se elimina el post
        if (post.getUserId().equals(idUser)) {
            entityManager.remove(post);
        }

        // Si el usuario no es dueño del post, entonces se lanza una excepción
        if (!post.getUserId().equals(idUser)) {
            // Si el usuario es administrador o moderador, entonces se desactiva el post
        

            if (!isModerator.isEmpty()) {
                post.setPostStateId(DESHABILITADO);
                entityManager.merge(post);
            } else {
                throw new ForbiddenException("User not allowed to delete this post");
            } 
        }
    }

    @Transactional
    public void hidePost(Long idPost) {
        Post post = entityManager.find(Post.class, idPost);
        if (post == null) {
            throw new PostNotFoundException("Post not found with id: " + idPost);
        }
        post.setPostStateId(PRIVADO);
        entityManager.merge(post);
    }

    @Transactional
    public void publishPost(Long idPost) {
        Post post = entityManager.find(Post.class, idPost);
        if (post == null) {
            throw new PostNotFoundException("Post not found with id: " + idPost);
        }
        post.setPostStateId(PUBLICO);
        entityManager.merge(post);
    }

    @Transactional
    public void saveComment(Comment comment) {
        comment.setId(0L);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setDeletedAt(null);

        entityManager.merge(comment);
    }

    public List<CommentSummary> getCommentsByPost(Long idPost) {
        return entityManager.createQuery("SELECT cs FROM CommentSummary cs WHERE cs.postId = :idPost", CommentSummary.class)
                .setParameter("idPost", idPost)
                .getResultList();
    }

    @Transactional
    public void deleteComment(Long idComment) {
        Comment comment = entityManager.find(Comment.class, idComment);
        if (comment == null) {
            throw new PostNotFoundException("Comment not found with id: " + idComment);
        }
        entityManager.remove(comment);

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

    public CommentSummary getCommentById(Long idComment) {
        CommentSummary comment = entityManager.find(CommentSummary.class, idComment);
        if (comment == null) {
            throw new PostNotFoundException("Comment not found with id: " + idComment);
        }
        return comment;
    }

    @Transactional
    public void likePost(Long idPost, Long idUser) {
        // Verificar si el usuario ya le dio like al post
        List<?> result = entityManager.createNativeQuery("SELECT * FROM USER_POST_LIKES WHERE USER_id_user = :idUser AND POST_id_post = :idPost")
                .setParameter("idUser", idUser)
                .setParameter("idPost", idPost)
                .getResultList();
        // Si ya le dio like, entonces se elimina el like
        if (!result.isEmpty()) {
            entityManager.createNativeQuery("DELETE FROM USER_POST_LIKES WHERE USER_id_user = :idUser AND POST_id_post = :idPost")
                    .setParameter("idUser", idUser)
                    .setParameter("idPost", idPost)
                    .executeUpdate();
        }
        // Si no le ha dado like, entonces se agrega el like
        if (result.isEmpty()) {
            entityManager.createNativeQuery("INSERT INTO USER_POST_LIKES (USER_id_user, POST_id_post) VALUES (:idUser, :idPost)")
                    .setParameter("idUser", idUser)
                    .setParameter("idPost", idPost)
                    .executeUpdate();
        }
    }

    

}
