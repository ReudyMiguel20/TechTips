package com.techtips.techtips.users.service.impl;

import com.techtips.techtips.users.exception.error.UserNotFoundException;
import com.techtips.techtips.users.model.dto.RegisterRequest;
import com.techtips.techtips.users.model.entity.User;
import com.techtips.techtips.users.repository.UserRepository;
import com.techtips.techtips.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public User registerNewUser(RegisterRequest newUser) {
        return modelMapper.map(newUser, User.class);
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

    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
}
