package com.project.server.config;

import com.project.server.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    JwtFilter jwtFilter;

    private static final String[] AUTH_LIST = {
            // -- swagger ui
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs",
            "/webjars/**",
            "/authentication/login",
            "/authentication/register",
            "/flower102/**",
            "favicon.ico:1"
    };
    @Bean
    public AuthenticationManager getManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
    {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception
    {
        httpSecurity.csrf().disable();

        //有验证的
        httpSecurity.authorizeRequests()
                .antMatchers(AUTH_LIST).permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //没有验证的
//        httpSecurity.authorizeRequests()
//                .anyRequest().permitAll()
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}

