package com.techtips.techtips.auth.service;

import com.techtips.techtips.auth.dto.AuthenticationToken;
import com.techtips.techtips.users.model.dto.RegisterRequest;

public interface AuthService {
    AuthenticationToken register(RegisterRequest request);
}
