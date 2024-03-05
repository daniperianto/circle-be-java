package com.ruangmain.cirle.controllers;

import com.ruangmain.cirle.dto.auth.UserDto;
import com.ruangmain.cirle.libs.jwt.JwtUtil;
import com.ruangmain.cirle.models.User;
import com.ruangmain.cirle.services.FollowService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/follow")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class FollowController {

    private final FollowService service;
    private final JwtUtil jwtUtil;

    @PostMapping("/add/{id}")
    public ResponseEntity<String> add(
            @PathVariable Long id,
            HttpServletRequest request) {
        var user = jwtUtil.getUser(request);
        return ResponseEntity.ok(service.add(user, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, HttpServletRequest request) {
        var user = jwtUtil.getUser(request);
        return ResponseEntity.ok(service.delete(user, id));
    }

    @GetMapping("/total-following/{id}")
    public ResponseEntity<Integer> findTotalFollowing(@PathVariable Long id) {
        return ResponseEntity.ok(service.findTotalFollowing(id));
    }

    @GetMapping("/total-followers/{id}")
    public ResponseEntity<Integer> findTotalFollowers(@PathVariable Long id) {
        return ResponseEntity.ok(service.findTotalFollowers(id));
    }

    @GetMapping("/suggested-accounts")
    public ResponseEntity<List<UserDto>> findSuggestedAccounts(HttpServletRequest request) {
        User user = jwtUtil.getUser(request);
        return ResponseEntity.ok(service.findSuggestedAccounts(user.getId()));
    }

    @GetMapping("/get-following")
    public ResponseEntity<List<UserDto>> findFollowing(HttpServletRequest request) {
        User user = jwtUtil.getUser(request);
        return ResponseEntity.ok(service.findFollowing(user));
    }

    @GetMapping("/get-followers")
    public ResponseEntity<List<UserDto>> findFollowers(HttpServletRequest request) {
        User user = jwtUtil.getUser(request);
        return ResponseEntity.ok(service.findFollowers(user));
    }

    @GetMapping("/is-following/{id}")
    public ResponseEntity<Boolean> isFollowing(@PathVariable Long id ,HttpServletRequest request) {
        User user = jwtUtil.getUser(request);
        return ResponseEntity.ok(service.isFollowing(user, id));
    }
}
