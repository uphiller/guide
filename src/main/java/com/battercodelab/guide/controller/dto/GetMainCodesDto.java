package com.battercodelab.guide.controller.dto;

import lombok.Data;

public class GetMainCodesDto {

    @Data
    public static class Response {
        private String code;
        private String name;
        private String expl;
    }
}
