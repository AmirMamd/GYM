package com.example.gym.Services.Jwt;


import com.example.gym.Entities.Users;
import com.example.gym.Repositories.UsersRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {
    UsersRepository userRepo;

    public UserDetailsServiceImpl(UsersRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsernameOrEmail(username,username);
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        GrantedAuthority grantedAuthority =new SimpleGrantedAuthority(user.getRole().name());
        grantedAuthorityList.add(grantedAuthority);
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),grantedAuthorityList);
    }
}