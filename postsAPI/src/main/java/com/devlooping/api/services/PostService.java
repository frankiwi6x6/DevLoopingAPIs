
package com.devlooping.api.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devlooping.api.dao.PostDAO;
import com.devlooping.api.entity.Comment;
import com.devlooping.api.entity.CommentSummary;
import com.devlooping.api.entity.Post;
import com.devlooping.api.entity.PostSummary;
import com.devlooping.api.websocket.PostHandler;

@Service
public class PostService {

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private PostHandler postHandler;

    public List<PostSummary> getAllPosts() {
        return postDAO.getAllPostSummaries();
    }

    public List<PostSummary> getPostsByUser(Long idUser) {
        return postDAO.getPostSummariesByUser(idUser);
    }

    public List<PostSummary> getAllPublishedPosts() {
        return postDAO.getAllPublishedPostSummaries();
    }

    public List<PostSummary> getPublishedPostsByUser(Long idUser) {
        return postDAO.getPublishedPostSummariesByUser(idUser);
    }

    public PostSummary getPostById(Long idPost) {
        return postDAO.getPostSummaryById(idPost);
    }

    public void savePost(Post post) {
        postDAO.savePost(post);
    }

    public void updatePost(Long idPost, String postContent, Long idUser) {
        postDAO.updatePost(idPost, idUser, postContent);
    }

    public void deletePost(Long idPost) {
        postDAO.deletePost(idPost);
    }

    public void publishPost(Long idPost) {
        postDAO.publishPost(idPost);
    }

    public void hidePost(Long idPost) {
        postDAO.hidePost(idPost);
    }

    public void saveComment(Comment comment) {
        postDAO.saveComment(comment);
        try {
            postHandler.sendComment(comment);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteComment(Long idComment) {
        postDAO.deleteComment(idComment);
    }

    public void updateComment(Long idComment, String commentContent) {
        postDAO.updateComment(idComment, commentContent);
    }

    public List<CommentSummary> getCommentsByPost(Long idPost) {
        return postDAO.getCommentsByPost(idPost);
    }

    public CommentSummary getCommentById(Long idComment) {
        return postDAO.getCommentById(idComment);
    }
}
