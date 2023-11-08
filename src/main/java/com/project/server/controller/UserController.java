package com.project.server.controller;

import com.project.server.entity.User;
import com.project.server.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("user")
@Api("用户信息接口")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping(value="/register",produces = "text/plain;charset=UTF-8")
    @ApiOperation("用户注册")
    public ResponseEntity<String> register(@RequestBody User user){
        if(userService.addUser(user))
            return ResponseEntity.ok("用户注册成功");
        else return ResponseEntity.badRequest().body("用户名已存在");
    }

    @PostMapping(value="/update",produces = "text/plain;charset=UTF-8")
    @ApiOperation("修改用户信息")
    public ResponseEntity<String> update(String name,@RequestBody User user)
    {
        try{
            return ResponseEntity.ok(userService.updateUser(name,user));
        }
        catch(Exception ex)
        {
            if (ex instanceof EmptyResultDataAccessException)
                return ResponseEntity.badRequest().body("要更新的用户不存在");
            else return ResponseEntity.badRequest().body("更新的新用户名已被使用");
        }
    }

}
