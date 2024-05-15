package com.devlooping.api.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.devlooping.api.entity.Post;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class PostDAOJpaImpl implements PostDAO {

    private EntityManager entityManager;

    public PostDAOJpaImpl(EntityManager eManager) {
        entityManager = eManager;
    }

    @Override
    public List<Post> buscarTodo() {
        TypedQuery<Post> consulta = entityManager.createQuery("from Post", Post.class);
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
        TypedQuery<Post> consulta = entityManager.createQuery("delete from Post where id_post = :id", Post.class);
        consulta.setParameter("id", id);
        consulta.executeUpdate();
    }

    @Override
    public List<Post> buscarPorUsuario(int idUsuario) {
        TypedQuery<Post> consulta = entityManager.createQuery("from Post where userId = :userId", Post.class);
        consulta.setParameter("userId", idUsuario);
        return consulta.getResultList();
    }

}
