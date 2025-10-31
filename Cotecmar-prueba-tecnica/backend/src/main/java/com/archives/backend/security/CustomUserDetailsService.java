package com.archives.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.archives.backend.security.model.UserModel;
import com.archives.backend.security.repository.IUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Error to get user!"));

        return new CustomUserDetails(userModel);
    }
}
