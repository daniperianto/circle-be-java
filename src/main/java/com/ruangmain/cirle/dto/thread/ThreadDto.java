package com.ruangmain.cirle.dto.thread;

import com.ruangmain.cirle.dto.auth.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThreadDto {
    private Long id;
    private UserDto user;
    private String content;
    private String image;
    private Date created_at;
}
