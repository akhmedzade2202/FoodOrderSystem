package com.example.foodordersystem.service.security;

import com.example.foodordersystem.dto.security.AuthResponse;
import com.example.foodordersystem.dto.security.LoginDTO;
import com.example.foodordersystem.dto.security.RegisterDTO;

public interface AuthService {
    AuthResponse register(RegisterDTO request);
    AuthResponse login(LoginDTO request);

}
