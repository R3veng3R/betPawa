package com.aj.pawatask.services.impl;

import com.aj.pawatask.models.User;
import com.aj.pawatask.repositories.UserRepository;
import com.aj.pawatask.services.UserService;
import com.aj.pawatask.utils.errors.EmailNotFoundExcetion;
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
        user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User getUserByEmail(String email) {
        log.info("Getting data for email: " + email);

        if (email == null || !userRepository.existsByEmail(email)) {
            throw new EmailNotFoundExcetion();
        }

        return userRepository.findByEmail(email);
    }
}
