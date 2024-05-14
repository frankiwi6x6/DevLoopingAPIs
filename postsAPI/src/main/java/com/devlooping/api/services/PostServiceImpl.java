package com.devlooping.api.services;

import java.util.List;

import com.devlooping.api.dao.PostDAO;
import com.devlooping.api.entity.Post;

import jakarta.transaction.Transactional;



public class PostServiceImpl implements PostService{
    private PostDAO dao;



    public PostServiceImpl(PostDAO postDAO
    ){dao = postDAO;}
    

    @Override 
    @Transactional

    public List<Post> buscarTodo() {
        return dao.buscarTodo();
  }

    

    @Override 
    @Transactional
    public Post buscarPorId(int id) {
        return dao.buscarPorId(id);
    }
  

    @Override
    @Transactional
    public void guardarPost(Post newpost) {
        dao.guardarPost(newpost);
  }

    @Override
    @Transactional
    public void borrarPost(int id) {
        dao.borrarPost(id);
    }
  

    @Override
    @Transactional
    public List<Post> buscarPorUsuario(int idUsuario) {
        return dao.buscarPorUsuario(idUsuario);
  }
}

    

