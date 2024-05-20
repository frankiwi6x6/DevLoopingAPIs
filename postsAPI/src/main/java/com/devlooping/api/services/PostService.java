package com.devlooping.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.devlooping.api.dao.PostDAO;
import com.devlooping.api.entity.Post;
import com.devlooping.api.entity.PostSummary;

@Service
public class PostService {

    @Autowired
    private PostDAO postDAO;

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

}
