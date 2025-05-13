package com.example.foodordersystem.service.security;

import io.jsonwebtoken.Claims;
import com.example.foodordersystem.entity.User;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;



import java.util.List;


public interface JwtService {
    String extractUsername(String token);

    <T> T extractClaim(String token, Function<Claims, T> resolver);

    List<String> getRolesFromToken(String token);

    boolean isValid(String token, UserDetails user);

    String generateToken(User user);



}