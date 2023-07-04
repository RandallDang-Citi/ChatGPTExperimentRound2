package com.citi.copilot.config;

import lombok.Data;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Data
@Component
public class SecretKey implements ApplicationListener<ApplicationStartedEvent> {

    private String secretKey;


    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        String baseStr = "TTS Tech China - THE BEST CSC";
        // 用baseStr 生成一个JWT secret key
        this.secretKey = Base64.getEncoder().encodeToString(baseStr.getBytes());
    }
}
