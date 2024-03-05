package com.ruangmain.cirle.controllers;

import com.ruangmain.cirle.dto.thread.ThreadDto;
import com.ruangmain.cirle.dto.thread.ThreadRequestDto;
import com.ruangmain.cirle.libs.jwt.JwtUtil;
import com.ruangmain.cirle.models.Thread;
import com.ruangmain.cirle.services.ThreadService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/thread")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ThreadController {

    private final JwtUtil jwtUtil;
    private final ThreadService service;



    @PostMapping(value = "/add")
    public ResponseEntity<String> add(
            HttpServletRequest request,
            @RequestParam(required = false,name = "content") String content,
            @RequestParam(required = false,name = "image") MultipartFile image) {
        var user = jwtUtil.getUser(request);
        var threadRequest = new ThreadRequestDto();
        if(!content.isEmpty()) threadRequest.setContent(content);
        if(image != null) threadRequest.setImage(image);
        return ResponseEntity.ok(service.add(threadRequest, user));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ThreadDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Thread> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Iterable<Thread>> findByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findByUserId(id));
    }

    @GetMapping("/following")
    public ResponseEntity<List<ThreadDto>> findByFollowing(HttpServletRequest request) {
        var user = jwtUtil.getUser(request);
        return ResponseEntity.ok(service.findByFollowing(user));
    }






}
