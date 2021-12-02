package com.sparta.guide.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class ArticleRequestDto {
    private String title;
    private String content;
    private MultipartFile image;
}
