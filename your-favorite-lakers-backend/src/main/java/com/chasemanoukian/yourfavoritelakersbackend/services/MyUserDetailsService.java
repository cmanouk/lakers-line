package com.chasemanoukian.yourfavoritelakersbackend.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(username);
        System.out.println(password);

        return (User) User.withDefaultPasswordEncoder()
                .username(username)
                .password(password)
                .roles("admin")
                .build();
    }
}
