package com.techtips.techtips.users.service;

import com.techtips.techtips.users.model.dto.RegisterRequest;
import com.techtips.techtips.users.model.entity.User;

public interface UserService {
    User registerNewUserAndAssignRole(RegisterRequest newUser);

    Object save(User registeredUser);

    void deleteAllUsers();

    User findByEmail(String email);
}
