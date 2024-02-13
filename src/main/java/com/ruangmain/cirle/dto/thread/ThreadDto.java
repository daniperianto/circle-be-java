package com.ruangmain.cirle.dto.thread;

import com.ruangmain.cirle.dto.auth.RegisteredUserDto;
import com.ruangmain.cirle.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThreadDto {
    private Long id;
    private RegisteredUserDto user;
    private String content;
    private String image;
}
