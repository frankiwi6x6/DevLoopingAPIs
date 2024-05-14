package com.devlooping.api.services;

import java.util.List;

import com.devlooping.api.entity.Post;

public interface PostService {
    
   List<Post> buscarTodo();

   Post buscarPorId (int id);

   void guardarPost (Post newpost);

   void borrarPost (int id);

   List<Post> buscarPorUsuario (int idUsuario);
    
}

