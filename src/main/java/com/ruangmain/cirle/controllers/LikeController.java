package com.ruangmain.cirle.controllers;

import com.ruangmain.cirle.libs.jwt.JwtUtil;
import com.ruangmain.cirle.models.User;
import com.ruangmain.cirle.services.LikeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/like")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class LikeController {
    private final LikeService service;
    private final JwtUtil jwtUtil;

    @GetMapping("/thread/{id}/count")
    public ResponseEntity<Integer> getThreadLikes(@PathVariable Long id) {
        return ResponseEntity.ok(service.getThreadLikes(id));
    }

    @PostMapping("/thread/{id}")
    public ResponseEntity<String> addLike(@PathVariable Long id, HttpServletRequest request) {
        User user = jwtUtil.getUser(request);
        return ResponseEntity.ok(service.addLike(user, id));
    }

    @DeleteMapping("/thread/{id}")
    public ResponseEntity<String> deleteLike(@PathVariable Long id, HttpServletRequest request) {
        User user = jwtUtil.getUser(request);
        return ResponseEntity.ok(service.deleteLike(user, id));
    }

    @GetMapping("/thread/{id}")
    public ResponseEntity<Boolean> isLiked(@PathVariable Long id, HttpServletRequest request) {
        User user = jwtUtil.getUser(request);
        return ResponseEntity.ok(service.isLiked(user, id));
    }

}
