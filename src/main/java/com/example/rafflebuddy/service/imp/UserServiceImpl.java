package com.example.rafflebuddy.service.imp;

import com.example.rafflebuddy.model.User;
import com.example.rafflebuddy.repository.UserRepository;
import com.example.rafflebuddy.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
