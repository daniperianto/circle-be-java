package com.ruangmain.cirle.libs.jwt;

import com.ruangmain.cirle.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtService service;

    public User getUser(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        var token = authHeader.substring(7);
        return service.extractId(token);
    }
}
