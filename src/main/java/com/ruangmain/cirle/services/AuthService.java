package com.ruangmain.cirle.services;

import com.ruangmain.cirle.dto.auth.AuthResponseDto;
import com.ruangmain.cirle.dto.auth.RegisterRequestDto;
import com.ruangmain.cirle.dto.auth.UserDto;
import com.ruangmain.cirle.libs.jwt.JwtService;
import com.ruangmain.cirle.mapper.impl.UserMapper;
import com.ruangmain.cirle.models.Role;
import com.ruangmain.cirle.models.User;
import com.ruangmain.cirle.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public AuthResponseDto register(RegisterRequestDto request) {
        var user = User.builder()
                .fullname(request.getFullname())
                .username("@" + request.getFullname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.User)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponseDto.builder()
                .token(jwtToken)
                .registeredUser(userMapper.mapTo(user))
                .build();
    }

    public AuthResponseDto loginByEmail(String email, String password) {
        var user = repository.findByEmail(email).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponseDto.builder()
                .token(jwtToken)
                .registeredUser(userMapper.mapTo(user))
                .build();
    }

    public AuthResponseDto loginByUsername(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );
        var user = repository.findByUsername(username).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponseDto.builder()
                .token(jwtToken)
                .registeredUser(userMapper.mapTo(user))
                .build();
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<UserDto> search(String account) {
        List<User> users = repository.search(account);
        return users.stream().map(userMapper::mapTo).toList();
    }
}
