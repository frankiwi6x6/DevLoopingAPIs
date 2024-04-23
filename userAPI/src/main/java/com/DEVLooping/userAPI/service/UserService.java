package com.DEVLooping.userAPI.service;

import java.util.List;

import com.DEVLooping.userAPI.entity.User;

public interface UserService {

    List<User> findAll();

    User findById(int theId);

    User findByUsername(String theUsername);

    User findByEmail(String theEmail);

    User loginUser(String theEmail, String thePassword);

    User save(User theUser);

    void deleteById(int theId);


}
