package com.devlooping.api.dao;

import java.util.List;

import com.devlooping.api.entity.Post;

public interface PostDAO {
   List<Post> buscarTodo();
   Post buscarPorId (int id);
   void guardarPost (Post newpost);
   void borrarPost (int id);
   List<Post> buscarPorUsuario (int idUsuario);

   
}

