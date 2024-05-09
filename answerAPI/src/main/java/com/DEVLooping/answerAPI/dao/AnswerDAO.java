package com.DEVLooping.answerAPI.dao;

import java.util.List;

import com.DEVLooping.answerAPI.entity.Answer;

public interface AnswerDAO {

    List<Answer> findAll();

    Answer findById(int theId);

    List<Answer> findByChallengeId(int theId);

    List<Answer> findAllUserAnswers(int theId);

    Answer findByChallengeAndUserId(int challengeId, int userId);

    void save(Answer theAnswer);

    void deleteByChallengeAndUserId(int challengeId, int userId);
    
}
