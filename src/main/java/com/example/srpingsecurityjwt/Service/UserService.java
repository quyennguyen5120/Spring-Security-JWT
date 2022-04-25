package com.example.srpingsecurityjwt.Service;

import com.example.srpingsecurityjwt.Entity.UserEntity;
import com.example.srpingsecurityjwt.Repositorty.CustomDetailService;
import com.example.srpingsecurityjwt.Repositorty.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CustomDetailService(userRepository.findByUsername(username));
    }
}
