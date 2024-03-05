package com.ruangmain.cirle.mapper.impl;

import com.ruangmain.cirle.dto.auth.UserDto;
import com.ruangmain.cirle.mapper.Mapper;
import com.ruangmain.cirle.models.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper implements Mapper<User, UserDto> {

    private final ModelMapper modelMapper;

    @Override
    public UserDto mapTo(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User mapFrom(UserDto registeredUserDto) {
        return null;
    }
}
