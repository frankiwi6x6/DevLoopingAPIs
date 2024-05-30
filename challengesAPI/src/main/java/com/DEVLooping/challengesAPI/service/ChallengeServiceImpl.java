package com.DEVLooping.challengesAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DEVLooping.challengesAPI.dao.ChallengeDAO;
import com.DEVLooping.challengesAPI.dao.GenericDAO;
import com.DEVLooping.challengesAPI.entity.Category;
import com.DEVLooping.challengesAPI.entity.Challenge;
import com.DEVLooping.challengesAPI.entity.ChallengeRequest;
import com.DEVLooping.challengesAPI.entity.Inputs;
import com.DEVLooping.challengesAPI.entity.Outputs;
import com.DEVLooping.challengesAPI.entity.Test;

import jakarta.transaction.Transactional;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    private GenericDAO<Category> categoryDAO;
    @Autowired
    private GenericDAO<Inputs> inputsDAO;
    @Autowired
    private GenericDAO<Outputs> outputsDAO;
    @Autowired
    private GenericDAO<Test> testDAO;
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

    @Override
    public void createChallenge(ChallengeRequest request) {
        Category category = request.getChallenge().getCategory();
        if (category != null) {
            categoryDAO.save(category);
        }

        Challenge challenge = request.getChallenge();
        challengeDAO.save(challenge);

        if (request.getInputs() != null) {
            for (Inputs input : request.getInputs()) {
                inputsDAO.save(input);
            }
        }

        if (request.getOutputs() != null) {
            for (Outputs output : request.getOutputs()) {
                outputsDAO.save(output);
            }
        }

        if (request.getTests() != null) {
            for (Test test : request.getTests()) {
                testDAO.save(test);
            }
        }
    }

}
