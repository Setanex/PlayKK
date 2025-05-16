package com.play.playkk.service.impl;

import com.play.playkk.model.BankSubscriber;
import com.play.playkk.repository.BankSubscriberRepository;
import com.play.playkk.service.BankSubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
import jakarta.transaction.Transactional;

// serwis operacji na bazie danych
@Service
@RequiredArgsConstructor
public class BankSubscriberServiceImpl implements BankSubscriberService {

    private final BankSubscriberRepository repository;

    public BankSubscriber getByRecipient(String recipient) {
        return repository.findByRecipient(recipient);
    }

    @Transactional
    public int updateStartStop(String recipient, String startStop) {
        return repository.updateStartStopByRecipient(startStop, recipient);
    }

    public BankSubscriber add(BankSubscriber bankSubscriber) {
       return repository.add(bankSubscriber);
    }
}
