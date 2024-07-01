package com.devlooping.api.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.devlooping.api.entity.Comment;
import com.devlooping.api.entity.CommentSummary;
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

    @DeleteMapping("/posts/{userId}/{idPost}")
    public ResponseEntity<?> deletePost(@PathVariable Long idPost, @PathVariable Long userId) {
        
        // Write a console Log 
        System.out.println("Delete Post: " + idPost + " by User: " + userId);
        
        try {
            postService.deletePost(idPost, userId);
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

    @GetMapping("/posts/{idPost}/comments")
    public ResponseEntity<?> getCommentsByPost(@PathVariable Long idPost) {
        List<CommentSummary> comments = postService.getCommentsByPost(idPost);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("/posts/{idPost}/comments")
    public ResponseEntity<?> saveComment(@RequestBody Comment comment, @PathVariable Long idPost) {
        comment.setPostId(idPost);
        postService.saveComment(comment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/posts/comments/{idComment}")
    public ResponseEntity<?> updateComment(@RequestBody String commentContent, @PathVariable Long idComment) {
        postService.updateComment(idComment, commentContent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/posts/comments/{idComment}")
    public ResponseEntity<?> deleteComment(@PathVariable Long idComment) {
        postService.deleteComment(idComment);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/posts/comments/{idComment}")
    public ResponseEntity<?> getCommentById(@PathVariable Long idComment) {
        CommentSummary comment = postService.getCommentById(idComment);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    // Likes and Dislikes

    @PutMapping("/posts/{idPost}/like/{idUser}")
    public ResponseEntity<?> likePost(@PathVariable Long idPost, @PathVariable Long idUser) {
        postService.likePost(idPost, idUser);
        return new ResponseEntity<>(HttpStatus.OK);
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
