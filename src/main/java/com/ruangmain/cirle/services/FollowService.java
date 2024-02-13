package com.ruangmain.cirle.services;

import com.ruangmain.cirle.models.Follow;
import com.ruangmain.cirle.models.User;
import com.ruangmain.cirle.repositories.FollowRepository;
import com.ruangmain.cirle.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository repository;
    private final UserRepository userRepository;

    public String add(User user, Long id) {
        var following = userRepository.findById(id).orElseThrow();
        Follow follow = Follow.builder()
                .followers(user)
                .following(following)
                .build();
        repository.save(follow);
        return "success";
    }

    public Integer findTotalFollowing(User user) {
        return repository.countByFollowing(user);
    }

    public Integer findTotalFollowers(User user) {
        return repository.countByFollowers(user);
    }

    public List<User> findSuggestedAccounts(Long userId) {
        var userIds = repository.findSuggestedAccountsId(userId);
        return findUsers(userIds);
    }

    public List<User> findFollowing(User user) {
        var userIds = repository.findFollowing(user.getId());
        return findUsers(userIds);
    }

    public List<User> findFollowers(User user) {
        var userIds = repository.findFollowers(user.getId());
        return findUsers(userIds);
    }

    public Boolean isFollowing(User user, Long id) {
        var count = repository.isFollowing(user.getId(), id);
        return count == 1;
    }

    private List<User> findUsers(Iterable<Long> ids) {
        return StreamSupport.stream(ids.spliterator(), false)
                .map(userRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }



}


