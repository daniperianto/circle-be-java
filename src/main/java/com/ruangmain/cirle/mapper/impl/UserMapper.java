package com.ruangmain.cirle.mapper.impl;

import com.ruangmain.cirle.dto.auth.RegisteredUserDto;
import com.ruangmain.cirle.mapper.Mapper;
import com.ruangmain.cirle.models.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper implements Mapper<User, RegisteredUserDto> {

    private final ModelMapper modelMapper;

    @Override
    public RegisteredUserDto mapTo(User user) {
        return modelMapper.map(user, RegisteredUserDto.class);
    }

    @Override
    public User mapFrom(RegisteredUserDto registeredUserDto) {
        return null;
    }
}
