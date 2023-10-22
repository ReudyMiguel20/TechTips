package com.techtips.techtips.users.service.impl;

import com.techtips.techtips.users.model.dto.RegisterRequest;
import com.techtips.techtips.users.model.entity.User;
import com.techtips.techtips.users.repository.UserRepository;
import com.techtips.techtips.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public User registerNewUser(RegisterRequest newUser) {
        User registeredUser = modelMapper.map(newUser, User.class);

        return registeredUser;
    }

    @Override
    public User save(User registeredUser) {
        return userRepository.save(registeredUser);
    }
}
