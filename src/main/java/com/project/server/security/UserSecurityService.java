package com.project.server.security;

import com.project.server.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.project.server.entity.User user=userService.getUser(username);
        if(user==null)
        {
            throw new UsernameNotFoundException("未找到用户:"+username);
        }
        return User.builder().
                username(username).
                password(new BCryptPasswordEncoder().encode(user.getPassword())).
                authorities("user").
                build();
    }
}
