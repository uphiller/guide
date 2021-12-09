package com.sparta.guide.controller;

import com.sparta.guide.dto.ChatMsgDto;
import com.sparta.guide.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final JwtTokenUtil jwtTokenUtil;
    String TOKEN_PREFIX = "Bearer ";

    @MessageMapping("/msg/{idx}")
    public void addUser(@DestinationVariable String idx, ChatMsgDto.Request chatMessage, @Header("Authorization") String token){
        String name = jwtTokenUtil.getUsernameFromToken(token.replace(TOKEN_PREFIX,""));
        ChatMsgDto.Response response = new ChatMsgDto.Response();
        response.setMsg(chatMessage.getMsg());
        response.setUsername(name);
        simpMessagingTemplate.convertAndSend("/topic/article/" + idx, response);
    }
}
