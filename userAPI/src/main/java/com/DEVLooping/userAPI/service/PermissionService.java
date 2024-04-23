package com.DEVLooping.userAPI.service;

import java.util.List;

import com.DEVLooping.userAPI.entity.Permission;

public interface PermissionService {

    List<Permission> findAll();

    Permission findById(int theId);

    Permission save(Permission thePermission);

    void deleteById(int theId);


}
