package com.DEVLooping.challengesAPI.dao;

import java.util.List;

import com.DEVLooping.challengesAPI.entity.User;

public interface UserDAO {

    List<User> findAll();

    User findById(int theId);

    User findByUsername(String theUsername);

    User loginUser(String theUsername, String thePassword);

    User save(User theUser);

    void deleteById(int theId);


 }
