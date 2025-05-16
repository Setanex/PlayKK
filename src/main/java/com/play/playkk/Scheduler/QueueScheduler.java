package com.play.playkk.Scheduler;

import com.play.playkk.model.SmsMessageIn;
import com.play.playkk.service.HttpClientService;
import com.play.playkk.service.SmsQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;

import java.io.IOException;

@Component
public class QueueScheduler {

    @Value("${url.out}")
    private String url;

    private final HttpClientService httpClientService;
    private final SmsQueueService smsQueueService;

    @Autowired
    public QueueScheduler(HttpClientService httpClientService, SmsQueueService smsQueueService) {
        this.httpClientService = httpClientService;
        this.smsQueueService = smsQueueService;
    }

    @Scheduled(fixedDelay = 1000)
    public void takeFromQueue() {

        final SmsMessageIn smsMessageIn = smsQueueService.dequeue();

        Gson gson = new Gson();

        if  ( smsMessageIn != null) {
            try {
                httpClientService.sendPostRequest(url,gson.toJson(smsMessageIn));
                smsQueueService.remove();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
