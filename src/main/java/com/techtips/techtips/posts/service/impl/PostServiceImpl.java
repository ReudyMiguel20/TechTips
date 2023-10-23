package com.techtips.techtips.posts.service.impl;

import com.techtips.techtips.posts.model.entity.Post;
import com.techtips.techtips.posts.service.PostService;
import com.techtips.techtips.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private UserRepository userRepository;


}
