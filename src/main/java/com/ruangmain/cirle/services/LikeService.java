package com.ruangmain.cirle.services;

import com.ruangmain.cirle.models.Like;
import com.ruangmain.cirle.models.LikePK;
import com.ruangmain.cirle.models.User;
import com.ruangmain.cirle.repositories.LikeRepository;
import com.ruangmain.cirle.repositories.ThreadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository repository;
    private final ThreadRepository threadRepository;

    public Integer getThreadLikes(Long id) {
        return repository.getThreadLikes(id);
    }

    public String addLike(User user, Long id) {
        Like like = Like.builder()
                .thread(threadRepository.findById(id).orElseThrow())
                .user(user).build();
        repository.save(like);
        return "success";
    }

    public String deleteLike(User user, Long id) {
        Like like = Like.builder()
                .thread(threadRepository.findById(id).orElseThrow())
                .user(user).build();
        repository.delete(like);
        return "success";
    }

    public Boolean isLiked(User user, Long id) {
        LikePK like = LikePK.builder()
                .thread(threadRepository.findById(id).orElseThrow())
                .user(user).build();
        var likes = repository.findById(like);
        return likes.isPresent();
    }
}
