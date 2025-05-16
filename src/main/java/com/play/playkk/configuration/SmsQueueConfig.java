package com.play.playkk.configuration;


import com.play.playkk.model.SmsMessageIn;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

// bean kolejki
@Configuration
public class SmsQueueConfig {

    @Bean
    public Queue<SmsMessageIn> SmsQueue() {
        return new ConcurrentLinkedQueue<>();
    }
}
