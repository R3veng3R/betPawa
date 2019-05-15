package com.aj.pawatask.services;

import com.aj.pawatask.models.User;

public interface UserService {
    User saveUser(User user);
    User getUserById(Long id);
}
