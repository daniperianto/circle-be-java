package com.ruangmain.cirle.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredUserDto {

    private Long id;
    private String username;
    private String fullname;
    private String email;
    private String photo_profile;
    private String background_image;
    private String bio;
}
