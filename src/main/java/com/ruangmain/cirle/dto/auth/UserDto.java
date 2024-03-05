package com.ruangmain.cirle.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String fullname;
    private String email;
    private String photo_profile;
    private String background_image;
    private String bio;
    private Date created_at;
}
