package com.sparta.guide.dto;

import lombok.Getter;
import lombok.Setter;

public class GetArticlesDto {

    @Getter
    @Setter
    public static class Response {
        private Long idx;
        private String title;
        private String imageUrl;
    }
}
