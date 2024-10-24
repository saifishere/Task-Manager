package com.user.service;

import com.user.config.JwtProvider;
import com.user.modal.User;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserProfile(String jwt) {

        String email = JwtProvider.getEmailFromJwtToken(jwt);

        User user = userRepository.findByEmail(email);

        return user;
    }

    @Override
    public List<User> getAllUser() {

        List<User> users = userRepository.findAll();
        return users;

    }
}
