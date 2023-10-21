package com.techtips.techtips.users.service.impl;

import com.techtips.techtips.users.repository.UserRepository;
import com.techtips.techtips.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


}
