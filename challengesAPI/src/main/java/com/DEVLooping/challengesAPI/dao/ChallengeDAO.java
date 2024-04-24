package com.DEVLooping.challengesAPI.dao;

import java.util.List;

import com.DEVLooping.challengesAPI.entity.Challenge;

public interface ChallengeDAO {

    List<Challenge> findAll();

    Challenge findById(int theId);

    List<Challenge> findByDifficulty(int theDifficulty);

    Challenge save(Challenge theUser);

    void deleteById(int theId);


 }
