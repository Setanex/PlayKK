package com.play.playkk.repository;

import com.play.playkk.model.BankSubscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//operacje na bazie danych
@Repository
public interface BankSubscriberRepository extends JpaRepository<BankSubscriber, Long> {

    @Modifying
    @Query("UPDATE BankSubscriber b SET b.startstop = :startstop WHERE b.recipient = :recipient")
    int updateStartStopByRecipient(@Param("startstop") String startStop, @Param("recipient") String recipient);

    default BankSubscriber add(BankSubscriber subscriber) {
        return save(subscriber);
    }

    BankSubscriber findByRecipient(String recipient);
}
