package com.playkk;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.play.playkk.model.SmsMessageIn;
import com.play.playkk.service.impl.SmsQueueServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

// testuje kolejkę
import java.util.Queue;
public class SmsQueueServiceImplTest {
    private SmsQueueServiceImpl smsQueueService;

    @Mock
    private Queue<SmsMessageIn> mockQueue;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        smsQueueService = new SmsQueueServiceImpl(mockQueue);
    }

    @Test
    public void testEnqueue() {
        SmsMessageIn message = new SmsMessageIn();
        smsQueueService.enqueue(message);
        verify(mockQueue).add(message);
    }

    @Test
    public void testDequeue() {
        SmsMessageIn expectedMessage = new SmsMessageIn();
        when(mockQueue.peek()).thenReturn(expectedMessage);

        SmsMessageIn result = smsQueueService.dequeue();
        assertEquals(expectedMessage, result);
        verify(mockQueue).peek();
    }
}
