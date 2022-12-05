package com.battercodelab.guide.controller.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


public class PostArticleDto {
    @Getter
    @Setter
    public static class Request {
        private String title;
        private String content;
        private MultipartFile image;
    }
}
