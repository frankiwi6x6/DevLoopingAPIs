package com.DEVLooping.challengesAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DEVLooping.challengesAPI.dao.ChallengeDAO;
import com.DEVLooping.challengesAPI.entity.Challenge;
import com.DEVLooping.challengesAPI.repo.ChallengeRepository;

import jakarta.transaction.Transactional;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    private ChallengeDAO challengeDAO;
    @Autowired
    private ChallengeRepository challengeRepo;

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
