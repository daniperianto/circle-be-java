package com.ruangmain.cirle.controllers;

import com.ruangmain.cirle.dto.auth.AuthResponseDto;
import com.ruangmain.cirle.dto.auth.LoginDto;
import com.ruangmain.cirle.dto.auth.RegisterRequestDto;
import com.ruangmain.cirle.dto.auth.UserDto;
import com.ruangmain.cirle.models.User;
import com.ruangmain.cirle.services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(
            @RequestBody RegisterRequestDto request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(
            @RequestBody LoginDto request) {
        if(!(request.getEmail() == null)) {
            return ResponseEntity.ok(service.loginByEmail(
                    request.getEmail(),
                    request.getPassword()
            ));
        } else if(!(request.getUsername() == null)) {
            return ResponseEntity.ok(service.loginByUsername(
                    request.getUsername(),
                    request.getPassword()
            ));
        } else {
            return ResponseEntity.badRequest()
                    .body(AuthResponseDto.builder()
                            .token("error").build());
        }
    }

    @GetMapping("/check")
    public ResponseEntity<String> check() {
        return ResponseEntity.ok("token is valid");
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/search/{account}")
    public ResponseEntity<List<UserDto>> search(@PathVariable String account) {
        return ResponseEntity.ok(service.search(account));
    }
}
