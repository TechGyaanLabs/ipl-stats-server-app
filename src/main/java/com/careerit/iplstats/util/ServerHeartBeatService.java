package com.careerit.iplstats.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class ServerHeartBeatService {

    @Scheduled(fixedDelay = 1000 * 2 * 60)
    public void getHeartBeat(){
        log.info("Server is up and running {}", LocalDateTime.now());
    }

}
