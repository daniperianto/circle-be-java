package com.ruangmain.cirle.services;

import com.ruangmain.cirle.dto.auth.UserDto;
import com.ruangmain.cirle.mapper.impl.UserMapper;
import com.ruangmain.cirle.models.Follow;
import com.ruangmain.cirle.models.User;
import com.ruangmain.cirle.repositories.FollowRepository;
import com.ruangmain.cirle.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository repository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public String add(User user, Long id) {
        var following = userRepository.findById(id).orElseThrow();
        Follow follow = Follow.builder()
                .followers(user)
                .following(following)
                .build();
        repository.save(follow);
        return "success";
    }

    public String delete(User user, Long id) {
        var following = userRepository.findById(id).orElseThrow();
        Follow follow = Follow.builder()
                .followers(user)
                .following(following).build();
        repository.delete(follow);
        return "success";
    }

    public Integer findTotalFollowing(Long id) {
        return repository.countByFollowing(id);
    }

    public Integer findTotalFollowers(Long id) {
        return repository.countByFollowers(id);
    }

    public List<UserDto> findSuggestedAccounts(Long userId) {
        var userIds = repository.findSuggestedAccountsId(userId);
        return findUsers(userIds.stream().filter(id -> !id.equals(userId)).toList());
    }

    public List<UserDto> findFollowing(User user) {
        var userIds = repository.findFollowing(user.getId());
        return findUsers(userIds);
    }

    public List<UserDto> findFollowers(User user) {
        var userIds = repository.findFollowers(user.getId());
        return findUsers(userIds);
    }

    public Boolean isFollowing(User user, Long id) {
        var count = repository.isFollowing(user.getId(), id);
        return count == 1;
    }

    private List<UserDto> findUsers(List<Long> ids) {
        return ids.stream()
                .map(userRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(userMapper::mapTo)
                .toList();
    }



}


