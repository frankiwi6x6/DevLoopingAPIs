package com.DEVLooping.challengesAPI.service;

import java.util.List;

import com.DEVLooping.challengesAPI.entity.User;

public interface UserService {

    List<User> findAll();

    User findById(int theId);

    User findByUsername(String theUsername);

    User loginUser(String theUsername, String thePassword);

    User save(User theUser);

    void deleteById(int theId);


}
