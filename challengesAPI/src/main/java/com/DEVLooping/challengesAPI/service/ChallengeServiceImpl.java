package com.DEVLooping.challengesAPI.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.DEVLooping.challengesAPI.dao.ChallengeDAO;
import com.DEVLooping.challengesAPI.entity.Challenge;

import java.util.List;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    private ChallengeDAO challengeDAO;

    public ChallengeServiceImpl(ChallengeDAO theChallengeDAO) {
        challengeDAO = theChallengeDAO;

    }


    @Override
    public List<Challenge> findAll() {
        return challengeDAO.findAll();
    }




    @Override
    public Challenge findById(int theId) {
        return challengeDAO.findById(theId);
    }

    @Override
    public List<Challenge> findByDifficulty(int theDifficulty) {
        return challengeDAO.findByDifficulty(theDifficulty);
    }

    @Transactional
    @Override
    public Challenge save(Challenge theUserDAO) {
        return challengeDAO.save(theUserDAO);
    }

    @Transactional
    @Override
    public void deleteById(int theId) {
        challengeDAO.deleteById(theId);

    }

}
