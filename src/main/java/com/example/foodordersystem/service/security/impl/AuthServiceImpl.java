package com.example.foodordersystem.service.security.impl;

import com.example.foodordersystem.dto.security.AuthResponse;
import com.example.foodordersystem.dto.security.LoginDTO;
import com.example.foodordersystem.dto.security.RegisterDTO;
import com.example.foodordersystem.entity.User;
import com.example.foodordersystem.enums.Role;
import com.example.foodordersystem.exception.NotFoundException;
import com.example.foodordersystem.repository.UserRepository;
import com.example.foodordersystem.service.security.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;

    /// herbir register olan usere biz ozumuz default olaraq rolunu USER teyin edirik
    @Override
    public AuthResponse register(RegisterDTO request) {
        User user = new User();
        user.setRole(Role.USER);
        user.setPassword(request.getPassword());
        user.setUsername(request.getUsername());
        user.setBalance(0.0);
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return new AuthResponse("User register successfully !!");
    }

    @Override
    public AuthResponse login(LoginDTO request) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException(
                    "Invalid username or password. Please check your credentials and try again.");
        }

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(
                () -> new NotFoundException("Invalid username or password"));

        String accessToken = jwtService.generateToken(user);

        return new AuthResponse(accessToken);
    }


}
