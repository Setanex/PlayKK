package com.playkk;

import com.play.playkk.model.BankSubscriber;
import com.play.playkk.repository.BankSubscriberRepository;
import com.play.playkk.service.impl.BankSubscriberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

// testuje bazę danych
public class BankSubscriberServiceImplTest {

    private BankSubscriberRepository repository;
    private BankSubscriberServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(BankSubscriberRepository.class);
        service = new BankSubscriberServiceImpl(repository);
    }

    @Test
    void testGetByRecipient() {
        String recipient = "testowy@playkk.pl";
        BankSubscriber expected = new BankSubscriber();
        expected.setRecipient(recipient);

        when(repository.findByRecipient(recipient)).thenReturn(expected);

        BankSubscriber result = service.getByRecipient(recipient);

        assertNotNull(result);
        assertEquals(recipient, result.getRecipient());
        verify(repository).findByRecipient(recipient);
    }

    @Test
    void testUpdateStartStop() {
        String recipient = "testowy@playkk.pl";
        String startStop = "START";

        when(repository.updateStartStopByRecipient(startStop, recipient)).thenReturn(1);

        int result = service.updateStartStop(recipient, startStop);

        assertEquals(1, result);
        verify(repository).updateStartStopByRecipient(startStop, recipient);
    }

    @Test
    void testAdd() {
        BankSubscriber bankSubscriber = new BankSubscriber();
        bankSubscriber.setRecipient("testowy@playkk.pl");

        when(repository.add(bankSubscriber)).thenReturn(bankSubscriber);

        BankSubscriber result = service.add(bankSubscriber);

        assertNotNull(result);
        assertEquals("testowy@playkk.pl", result.getRecipient());
        verify(repository).add(bankSubscriber);
    }
}
