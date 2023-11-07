package com.project.server.service.impl;

import com.project.server.dao.UserJpaRepository;
import com.project.server.entity.User;
import com.project.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Transactional()
@Service
public class UserService implements IUserService {
    @Autowired
    UserJpaRepository userJPARepository;
    @Override
    public boolean addUser(User user) {
        try{
            userJPARepository.findById(user.getName()).get();
            return false;
        }
        catch (NoSuchElementException ex)
        {
            userJPARepository.save(user);
            return true;
        }
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
    public String updateUser(String name,User user) throws Exception
    {
        userJPARepository.deleteById(name);
        if(userJPARepository.findById(user.getName()).isPresent()) throw new RuntimeException();
        userJPARepository.save(user);
        return "信息修改成功";
    }

    @Override
    public List<User> findAllUser() {
        return null;
    }
}
