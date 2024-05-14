package com.devlooping.api.dao;

import java.util.List;

import com.devlooping.api.entity.Post;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class PostDAOJpaImpl implements PostDAO {

    private EntityManager entityManager;

    public PostDAOJpaImpl(EntityManager eManager) {
        entityManager = eManager;
     }
    

    @Override
    public List<Post> buscarTodo() {
        TypedQuery <Post> consulta = entityManager.createQuery("from POST",Post.class);
        return consulta.getResultList();
    }

    @Override
    public Post buscarPorId(int id) {
        return entityManager.find(Post.class, id);
    }

    @Override
    public void guardarPost(Post newpost) {
        Post dbPost = entityManager.merge(newpost);
        newpost.setId(dbPost.getId());
    }

    @Override
    public void borrarPost(int id) {
        TypedQuery <Post> consulta = entityManager.createQuery("delete from POST where id_post =:id_post",Post.class);
        consulta.setParameter("id_post", id);
        consulta.executeUpdate();
        
    }

    @Override
    public List<Post> buscarPorUsuario(int idUsuario) {
        TypedQuery <Post> consulta = entityManager.createQuery("from POST where USER_id_user =:USER_id_user",Post.class);
        consulta.setParameter("USER_id_user", idUsuario);
        return consulta.getResultList();
        
    }
    
}
