package com.DEVLooping.answerAPI.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.DEVLooping.answerAPI.dao.AnswerDAO;
import com.DEVLooping.answerAPI.entity.Answer;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    private AnswerDAO answerDAO;

    public AnswerServiceImpl(AnswerDAO theAnswerDAO) {
        answerDAO = theAnswerDAO;

    }


    @Override
    @Transactional
    public List<Answer> findAll() {
        return answerDAO.findAll();
    }
    
    @Override
    @Transactional
    public Answer findById(int theId) {
        return answerDAO.findById(theId);
    }

    @Override
    @Transactional
    public List<Answer> findByChallengeId(int theId) {
        return answerDAO.findByChallengeId(theId);
    }

    @Override
    @Transactional
    public List<Answer> findAllUserAnswers(int theId) {
        return answerDAO.findAllUserAnswers(theId);
    }

    @Override
    @Transactional
    public Answer findByChallengeAndUserId(int challengeId, int userId) {
        return answerDAO.findByChallengeAndUserId(challengeId, userId);
    }
    
    @Override
    @Transactional
    public void save(Answer theAnswer) {
        answerDAO.save(theAnswer);
    }

    @Override
    @Transactional
    public void deleteByChallengeAndUserId(int challengeId, int userId) {
        answerDAO.deleteByChallengeAndUserId(challengeId, userId);
    }
}
