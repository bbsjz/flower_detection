package com.project.server.service;

import com.project.server.entity.User;

import java.util.List;

public interface IUserService {
    boolean addUser(User user);

    void deleteUser(String name);

    User getUser(String name);

    String updateUser(String name, User user) throws Exception;

    List<User> findAllUser();
}
