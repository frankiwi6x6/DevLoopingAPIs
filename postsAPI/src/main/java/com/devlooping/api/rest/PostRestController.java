package com.devlooping.api.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import com.devlooping.api.entity.ErrorResponse;
import com.devlooping.api.entity.Post;
import com.devlooping.api.entity.PostSummary;
import com.devlooping.api.exception.ForbiddenException;
import com.devlooping.api.exception.PostNotFoundException;
import com.devlooping.api.services.PostService;

@RestController
@RequestMapping("/api")
public class PostRestController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<?> getAllPosts() {
        List<PostSummary> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts/published")
    public ResponseEntity<?> getAllPublishedPosts() {
        List<PostSummary> posts = postService.getAllPublishedPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts/published/user/{idUser}")
    public ResponseEntity<?> getPublishedPostsByUser(@PathVariable Long idUser) {
        List<PostSummary> posts = postService.getPublishedPostsByUser(idUser);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts/user/{idUser}")
    public ResponseEntity<?> getPostsByUser(@PathVariable Long idUser) {
        List<PostSummary> posts = postService.getPostsByUser(idUser);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts/{idPost}")
    public ResponseEntity<?> getPostById(@PathVariable Long idPost) {
        try {
            PostSummary post = postService.getPostById(idPost);
            return new ResponseEntity<>(post, HttpStatus.OK);
        } catch (PostNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Post Not Found", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/posts")
    public ResponseEntity<?> savePost(@RequestBody Post post) {
        try {
            postService.savePost(post);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/posts/{idPost}/{idUser}")
    public ResponseEntity<?> updatePost(@RequestBody String postContent, @PathVariable Long idPost, @PathVariable Long idUser) {
        try {
            postService.updatePost(idPost, postContent,idUser );
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (PostNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Post Not Found", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } catch (ForbiddenException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Forbidden", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/posts/{idPost}")
    public ResponseEntity<?> deletePost(@PathVariable Long idPost) {
        try {
            postService.deletePost(idPost);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (PostNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Post Not Found", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/posts/publish/{idPost}")
    public ResponseEntity<?> publishPost(@PathVariable Long idPost) {
        try {
            postService.publishPost(idPost);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (PostNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Post Not Found", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/posts/hide/{idPost}")
    public ResponseEntity<?> hidePost(@PathVariable Long idPost) {
        try {
            postService.hidePost(idPost);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (PostNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Post Not Found", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @ExceptionHandler(PostNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handlePostNotFoundException(PostNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Post Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ForbiddenException.class)
    public final ResponseEntity<ErrorResponse> handleForbiddenException(ForbiddenException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Forbidden", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
}