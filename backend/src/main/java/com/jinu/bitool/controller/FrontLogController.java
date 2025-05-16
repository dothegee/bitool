package com.jinu.bitool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/front-log")
public class FrontLogController {

    @PostMapping
    public void receiveFrontLog(@RequestBody Map<String, Object> logData) {
        log.info("📝 [프론트 로그] [{}] {} - meta={}",
                logData.get("level"),
                logData.get("message"),
                logData.get("meta"));
    }
}
