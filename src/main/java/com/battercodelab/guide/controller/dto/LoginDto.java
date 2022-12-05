package com.battercodelab.guide.controller.dto;

import lombok.Data;

public class LoginDto {

    @Data
    public static class Request {
        private String id;
        private String password;
    }

    @Data
    public static class Response {
        private String id;
        private String name;
        private String token;
    }
}
