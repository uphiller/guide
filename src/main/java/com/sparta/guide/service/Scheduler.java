package com.sparta.guide.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
@Slf4j
public class Scheduler {
    @Scheduled(cron = "0 05 18 ? * *")
    public void runScheudler(){
        log.error("스케줄러 동작 " + new Date());
    }
}
