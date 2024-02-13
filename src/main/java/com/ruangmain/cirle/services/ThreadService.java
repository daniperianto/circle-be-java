package com.ruangmain.cirle.services;

import com.ruangmain.cirle.dto.thread.ThreadDto;
import com.ruangmain.cirle.dto.thread.ThreadRequestDto;
import com.ruangmain.cirle.libs.cloudinary.CloudinaryService;
import com.ruangmain.cirle.mapper.impl.ThreadMapper;
import com.ruangmain.cirle.models.Thread;
import com.ruangmain.cirle.models.User;
import com.ruangmain.cirle.repositories.ThreadRepository;
import com.ruangmain.cirle.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ThreadService {

    private final ThreadRepository repository;
    private final UserRepository userRepository;
    private final CloudinaryService cloudinary;
    private final ThreadMapper mapper;

    public String add(ThreadRequestDto request, User user) {
        var thread = Thread.builder()
                .user(user)
                .content(request.getContent())
                .image(cloudinary.uploadFile(request.getImage()))
                .build();
        repository.save(thread);
        return "success";
    }

    public List<Thread> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), true)
                .collect(Collectors.toList());
    }

    public Thread findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Iterable<Thread> findByUserId(Long id) {
        var user = userRepository.findById(id).orElseThrow();
        return repository.findByUser(user);
    }

    public Iterable<Thread> findByFollowing(User user) {
        return repository.findByFollowing(user);
    }


}
