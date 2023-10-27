package com.techtips.techtips.users.service.impl;

import com.techtips.techtips.users.exception.error.UserNotFoundException;
import com.techtips.techtips.users.model.dto.RegisterRequest;
import com.techtips.techtips.users.model.entity.Role;
import com.techtips.techtips.users.model.entity.User;
import com.techtips.techtips.users.repository.UserRepository;
import com.techtips.techtips.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerNewUserAndAssignRole(RegisterRequest newUser) {
        User user = modelMapper.map(newUser, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        assignRole(user);
        save(user);

        return user;
    }

    @Override
    public User save(User registeredUser) {
        return userRepository.save(registeredUser);
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public void assignRole(User registeredUser) {
        if (getAllUsers().isEmpty()) {
            registeredUser.setRole(Role.ADMIN);
        } else {
            registeredUser.setRole(Role.USER);
        }
    }
}
