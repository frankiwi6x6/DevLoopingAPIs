package com.DEVLooping.challengesAPI.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import com.DEVLooping.challengesAPI.entity.Challenge;
import java.util.List;

@Repository
public class ChallengeDAOJpaImpl implements ChallengeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Challenge> findAll() {

        TypedQuery<Challenge> theQuery = entityManager.createQuery("FROM Challenge", Challenge.class);

        List<Challenge> challenges = theQuery.getResultList();

        return challenges;
    }

    @Override
    public Challenge findById(int theId) {

        Challenge challenge = entityManager.find(Challenge.class, theId);
        return challenge;
    }

    @Override
    public List<Challenge> findByDifficulty(int theDifficulty) {
        TypedQuery<Challenge> theQuery = entityManager.createQuery(
                "SELECT c FROM Challenge c JOIN c.difficulty d WHERE d.id = :difficulty", Challenge.class);
        theQuery.setParameter("difficulty", theDifficulty);
        return theQuery.getResultList();
    }
    @Override
    public Challenge save(Challenge theChallenge) {

        Challenge dbChallenge = entityManager.merge(theChallenge);

        return dbChallenge;
    }

    @Override
    public void deleteById(int theId) {

        Challenge dbChallenge = entityManager.find(Challenge.class, theId);

        entityManager.remove(dbChallenge);

    }
}

