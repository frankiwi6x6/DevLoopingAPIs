package com.DEVLooping.challengesAPI.dao;

import java.util.List;

import com.DEVLooping.challengesAPI.entity.Difficulty;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class DifficultyDAOJpaImpl implements DifficultyDAO {

    private EntityManager entityManager;
    

    @Override
    public List<Difficulty> findAll() {
        TypedQuery<Difficulty> theQuery = entityManager.createQuery("from Difficulty", Difficulty.class);
        List<Difficulty> difficulties = theQuery.getResultList();
        return difficulties;
    }

    @Override
    public Difficulty findById(int theId) {
        Difficulty difficulty = entityManager.find(Difficulty.class, theId);
        return difficulty;

    }
    @Override
    public Difficulty save(Difficulty theDifficulty) {
        Difficulty dbDifficulty = entityManager.merge(theDifficulty);
        return dbDifficulty;
        
    }

    @Override
    public void deleteById(int theId) {
        Difficulty dbDifficulty = entityManager.find(Difficulty.class, theId);
        entityManager.remove(dbDifficulty);

        
    }

}
