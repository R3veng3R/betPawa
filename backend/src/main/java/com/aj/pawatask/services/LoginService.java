package com.aj.pawatask.services;

import com.aj.pawatask.models.User;

public interface LoginService {
    User authenticate(String email);
}
