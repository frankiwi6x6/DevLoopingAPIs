package com.DEVLooping.answerAPI.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.DEVLooping.answerAPI.entity.Answer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class AnswerDAOJpaImpl implements AnswerDAO {

    private EntityManager entityManager;

    public AnswerDAOJpaImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Answer> findAll() {
        TypedQuery<Answer> theQuery = entityManager.createQuery("FROM Answer", Answer.class);
        return theQuery.getResultList();
    }

    @Override
    public Answer findById(int theId) {
        return entityManager.find(Answer.class, theId);
    }

    @Override
    public List<Answer> findByChallengeId(int theId) {
        TypedQuery<Answer> theQuery = entityManager.createQuery("FROM Answer WHERE id_challenge=:id_challenge", Answer.class);
        theQuery.setParameter("id_challenge", theId);
        return theQuery.getResultList();
    }

    @Override
    public List<Answer> findAllUserAnswers(int theId) {
        TypedQuery<Answer> theQuery = entityManager.createQuery("FROM Answer WHERE id_user=:id_user", Answer.class);
        theQuery.setParameter("id_user", theId);
        return theQuery.getResultList();
    }

    @Override
    public Answer findByChallengeAndUserId(int challenge, int userId) {
        TypedQuery<Answer> theQuery = entityManager.createQuery("FROM Answer WHERE id_user=:id_user AND id_challenge=:id_challenge", Answer.class);
        theQuery.setParameter("id_user", userId);
        theQuery.setParameter("id_challenge", challenge);
        List<Answer> results = theQuery.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public void save(Answer theAnswer) {
        Answer dbAnswer = entityManager.merge(theAnswer);
        theAnswer.setId(dbAnswer.getId());
    } 

    @Override
    public void deleteByChallengeAndUserId(int challengeId, int userId) {
        TypedQuery<Answer> theQuery = entityManager.createQuery("DELETE FROM Answer WHERE id_user=:id_user AND id_challenge=:id_challenge", Answer.class);
        theQuery.setParameter("id_user", userId);
        theQuery.setParameter("id_challenge", challengeId);
        theQuery.executeUpdate();
    }
}
