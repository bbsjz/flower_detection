package com.project.server.security;

import com.project.server.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authentication")
@Api("用户验证接口")
public class AuthenController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserSecurityService userSecurityService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
            UserDetails userDetails = userSecurityService.loadUserByUsername(user.getName());
            String token = jwtUtils.generateToken(userDetails);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("用户认证未通过");
        }
    }
}
