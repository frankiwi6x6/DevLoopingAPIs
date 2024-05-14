package com.devlooping.api.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devlooping.api.entity.Post;
import com.devlooping.api.services.PostService;



@RestController
@RequestMapping("/api")
public class PostRestController {
    private PostService service;


    public PostRestController(PostService pService){
        service = pService;
    }
  
    @GetMapping("/posts")
    public ResponseEntity<?> buscarTodo(){
        List<Post> posts = service.buscarTodo();

        return ResponseEntity.ok(posts);        
    }
    @GetMapping("/post/{postId}")
    public ResponseEntity<?> buscarPorId(int postId){
        Post post = service.buscarPorId(postId);
        if(post == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(post);
    }
    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<?> buscarPorUsuario(int userId){
        List<Post> posts = service.buscarPorUsuario(userId);
        if(posts.size() < 1){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(posts);
    }
    @PutMapping("/post/{postId}")
    public ResponseEntity<?> actualizarPost(@RequestBody Post post){
        Post postActualizado = service.actualizarPost(post);
        if(postActualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postActualizado);
    }
    @PostMapping("/post")
    public ResponseEntity<?> guardarPost(@RequestBody Post post){
        Post postGuardado = service.guardarPost(post);
        return ResponseEntity.ok(postGuardado);
    }
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<?> borrarPost(int postId){
        Post post = service.buscarPorId(postId);
        if(post == null){
            return ResponseEntity.notFound().build();
        }
        service.borrarPost(postId);
        return ResponseEntity.ok(post);
    }

    

}
