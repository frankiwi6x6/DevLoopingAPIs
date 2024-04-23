package com.DEVLooping.challengesAPI.dao;

import java.util.List;

import com.DEVLooping.challengesAPI.entity.Difficulty;

public interface DifficultyDAO {

    List<Difficulty> findAll();

    Difficulty findById(int theId);

    Difficulty save(Difficulty theDifficulty);

    void deleteById(int theId);

}
