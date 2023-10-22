package com.project.server.service;

import com.project.server.entity.User;

import java.util.List;

public interface IUserService {
    User addUser(User user);

    void deleteUser(String name);

    User getUser(String name);

    User updateUser(String name, User user);

    List<User> findAllUser();
}
