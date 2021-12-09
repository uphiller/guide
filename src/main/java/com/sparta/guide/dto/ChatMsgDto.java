package com.sparta.guide.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMsgDto {
    @Getter
    @Setter
    public static class Request {
        private String msg;
    }

    @Getter
    @Setter
    public static class Response {
        private String msg;
        private String username;
    }

}
