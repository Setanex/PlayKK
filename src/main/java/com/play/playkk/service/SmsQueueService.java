package com.play.playkk.service;

import com.play.playkk.model.SmsMessageIn;

public interface SmsQueueService {

    void enqueue(SmsMessageIn smg);
    SmsMessageIn dequeue();
    boolean isEmpty();
    void remove();

}
