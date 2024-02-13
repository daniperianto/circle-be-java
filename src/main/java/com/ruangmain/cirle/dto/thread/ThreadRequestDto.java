package com.ruangmain.cirle.dto.thread;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThreadRequestDto {
    private String content;
    private MultipartFile image;
}
