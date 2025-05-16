package com.play.playkk.service;

import com.play.playkk.model.BankSubscriber;

import java.util.Optional;

public interface BankSubscriberService {

    BankSubscriber getByRecipient(String recipient);
    int updateStartStop(String recipient, String startStop);
    BankSubscriber add(BankSubscriber bankSubscriber);
}
