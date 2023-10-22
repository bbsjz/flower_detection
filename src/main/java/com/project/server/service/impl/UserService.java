package com.project.server.service.impl;

import com.project.server.dao.UserJpaRepository;
import com.project.server.entity.User;
import com.project.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    UserJpaRepository userJPARepository;
    @Override
    public User addUser(User user) {
        return null;
    }

    @CacheEvict(cacheNames = "user",key="#name")
    public void deleteUser(String name)
    {
        userJPARepository.deleteById(name);
    }

    @Cacheable(cacheNames = "user",key = "#name",condition = "#name!=null")
    public User getUser(String name)
    {
        return userJPARepository.getReferenceById(name);
    }

    @CacheEvict(cacheNames = "user",key="#name")
    public User updateUser(String name,User user)
    {
        userJPARepository.deleteById(name);
        userJPARepository.save(user);
        return user;
    }

    @Override
    public List<User> findAllUser() {
        return null;
    }
}
