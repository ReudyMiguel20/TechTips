package com.techtips.techtips.users.service;

import com.techtips.techtips.users.model.dto.RegisterRequest;
import com.techtips.techtips.users.model.entity.User;

public interface UserService {
    User registerNewUser(RegisterRequest newUser);

    Object save(User registeredUser);
}
