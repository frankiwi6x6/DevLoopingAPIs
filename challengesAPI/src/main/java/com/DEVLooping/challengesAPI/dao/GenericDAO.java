package com.DEVLooping.challengesAPI.dao;

import java.util.List;

public interface GenericDAO<T> {
    void save(T entity);
    T findById(Class<T> entityClass, int id);
    List<T> findAll(Class<T> entityClass);
    void update(T entity);
    void delete(T entity);
}
