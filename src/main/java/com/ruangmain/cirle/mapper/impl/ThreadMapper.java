package com.ruangmain.cirle.mapper.impl;

import com.ruangmain.cirle.dto.thread.ThreadDto;
import com.ruangmain.cirle.mapper.Mapper;
import com.ruangmain.cirle.models.Thread;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ThreadMapper implements Mapper<Thread, ThreadDto> {

    private final ModelMapper modelMapper;

    @Override
    public ThreadDto mapTo(Thread thread) {
        modelMapper.typeMap(Thread.class, ThreadDto.class)
                .addMapping(Thread::getUser, ThreadDto::setUser);
        return modelMapper.map(thread, ThreadDto.class);
    }

    @Override
    public Thread mapFrom(ThreadDto threadDto) {
        return null;
    }
}
