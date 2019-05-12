package com.aj.pawatask.services.impl;

import com.aj.pawatask.models.User;
import com.aj.pawatask.repositories.UserRepository;
import com.aj.pawatask.services.UserService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Log
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
