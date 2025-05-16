package com.play.playkk.service.impl;

import com.play.playkk.model.SmsMessageIn;
import com.play.playkk.service.SmsQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Queue;

// serwis kolejkowy
@Service
public class SmsQueueServiceImpl implements SmsQueueService {

    private final Queue<SmsMessageIn> queue;

    @Autowired
    public SmsQueueServiceImpl(Queue<SmsMessageIn> queue) {
        this.queue = queue;
    }

    public void enqueue(SmsMessageIn smg) {
        queue.add(smg);
    }

    public SmsMessageIn dequeue() {
        return queue.peek(); // Zwraca null, jeśli kolejka jest pusta
    }

    public void remove() {
        queue.remove(); // Zwraca null, jeśli kolejka jest pusta
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
