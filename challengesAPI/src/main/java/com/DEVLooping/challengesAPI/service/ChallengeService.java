package com.DEVLooping.challengesAPI.service;

import java.util.List;

import com.DEVLooping.challengesAPI.entity.Challenge;
import com.DEVLooping.challengesAPI.entity.ChallengeRequest;

public interface ChallengeService {

    List<Challenge> findAll();

    Challenge findById(int theId);
    
    List<Challenge> findByDifficulty(int theDifficulty);
    
    Challenge save(Challenge theUser);

    void deleteById(int theId);

    void createChallenge(ChallengeRequest request);

}
