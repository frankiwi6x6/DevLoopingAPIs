package com.DEVLooping.challengesAPI.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.DEVLooping.challengesAPI.entity.ChallengeSummary;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ChallengeSummaryDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ChallengeSummary> findAll() {

        return entityManager.createQuery("SELECT cs FROM ChallengeSummary cs", ChallengeSummary.class)
                .getResultList();
    }

    public ChallengeSummary findById(int id) {
        return entityManager.find(ChallengeSummary.class, id);

    }

    public void save(ChallengeSummary challengeSummary) {
    }

    public void deleteById(int id) {
    }
}
