package com.DEVLooping.challengesAPI.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Transactional
public class GenericDAOImpl<T> implements GenericDAO<T> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(T entity) {
        if (!entityManager.contains(entity)) {
            entity = entityManager.merge(entity);
        }
        entityManager.persist(entity);
    }
    @Override
    public T findById(Class<T> entityClass, int id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public List<T> findAll(Class<T> entityClass) {
        return entityManager.createQuery("FROM " + entityClass.getName(), entityClass).getResultList();
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity); // Usamos merge para actualizar la entidad
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }
}
