package com.devlooping.api.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devlooping.api.entity.ErrorResponse;
import com.devlooping.api.entity.Post;
import com.devlooping.api.services.PostService;

@RestController
@RequestMapping("/api")
public class PostRestController {

    private final PostService service;
    ErrorResponse errorResponse;

    public PostRestController(PostService pService) {
        this.service = pService;
    }

    @GetMapping("/posts")
    public ResponseEntity<?> buscarTodo() {
        List<Post> posts = service.buscarTodo();
        if (posts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                            HttpStatus.NOT_FOUND.getReasonPhrase(),
                            "No hay posts disponibles"));
        }
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> buscarPorId(@PathVariable int postId) {
        Post post = service.buscarPorId(postId);
        if (post == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                            HttpStatus.NOT_FOUND.getReasonPhrase(),
                            "No se encontró el post con ID: " + postId));
        }
        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<?> buscarPorUsuario(@PathVariable int userId) {
        List<Post> posts = service.buscarPorUsuario(userId);
        if (posts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                            HttpStatus.NOT_FOUND.getReasonPhrase(),
                            "No se encontraron posts para el usuario con ID: " + userId));
        }
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/post")
    public ResponseEntity<?> guardarPost(@RequestBody Post post) {
        service.guardarPost(post);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/post")
    public ResponseEntity<?> actualizarPost(@RequestBody Post post) {
        Post postActual = service.buscarPorId(post.getId());
        if (postActual == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                            HttpStatus.NOT_FOUND.getReasonPhrase(),
                            "No se encontró el post con ID: " + post.getId()));
        }
        service.guardarPost(post);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<?> borrarPost(@PathVariable int postId) {
        Post post = service.buscarPorId(postId);
        if (post == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                            HttpStatus.NOT_FOUND.getReasonPhrase(),
                            "No se encontró el post con ID: " + postId));
        }
        service.borrarPost(postId);
        return ResponseEntity.ok(post);
    }

}
