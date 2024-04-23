package com.DEVLooping.userAPI.service;

import java.util.List;

import com.DEVLooping.userAPI.entity.UserType;

public interface UserTypeService {

    List<UserType> findAll();

    UserType findById(int theId);

    UserType save(UserType theUser);

    void deleteById(int theId);


}
