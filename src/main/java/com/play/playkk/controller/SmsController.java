package com.play.playkk.controller;

import com.google.gson.Gson;
import com.play.playkk.model.*;
import com.play.playkk.service.BankSubscriberService;
import com.play.playkk.service.HttpClientService;
import com.play.playkk.service.SmsQueueService;
import com.play.playkk.service.UrlDetector;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/in")
public class SmsController {

    @Value("${phone.bank}")
    private String bankPhone;

    @Value("${url.cloudgoogle}")
    private String googleCloud;

    @Value("${api.key}")
    private String apiKey;


    private final BankSubscriberService bankSubscriberService;
    private final SmsQueueService smsQueueService;
    private final HttpClientService httpClientService;
    private  final UrlDetector urlDetector;

    @Autowired
    public SmsController(BankSubscriberService bankSubscriberService, SmsQueueService smsQueueService,
                         HttpClientService httpClientService, UrlDetector urlDetector) {
        this.bankSubscriberService = bankSubscriberService;
        this.smsQueueService = smsQueueService;
        this.httpClientService = httpClientService;
        this.urlDetector = urlDetector;
    }
    // odbiera nadchodzące smsy i filtruje wg wymagań zadania czyli niektóre smsy akceptuje tzn wrzuca na koniec kolejki
    // a te nie spełniające warunków odrzuca czyli nie dołazca do kolejki.
    @PostMapping("/sms")
    public ResponseEntity<String> receiveSms(@RequestBody SmsMessageIn smsMessage) {

        if (smsMessage.getRecipient().equals(bankPhone)) {
            final BankSubscriber bySender = bankSubscriberService.getByRecipient(smsMessage.getSender());
            if (bySender != null) {
                if (smsMessage.getMessage().equals(StartStop.START.name()) || smsMessage.getMessage().equals(StartStop.STOP.name())) {
                    bankSubscriberService.updateStartStop(smsMessage.getSender(), smsMessage.getMessage());
                    smsQueueService.enqueue(smsMessage);
                    return ResponseEntity.ok("bankSubscriber successfully updated ");
                }
            }
        }
        final BankSubscriber byRecipient = bankSubscriberService.getByRecipient(smsMessage.getRecipient());
        if (byRecipient != null) {
            String detectedUrl = urlDetector.detectUrl(smsMessage.getMessage());
            if (detectedUrl != null) {
                PhishingRequest phishingRequest = PhishingRequest.builder()
                        .uri(detectedUrl)
                        .threatTypes(List.of(ThreatType.MALWARE, ThreatType.UNWANTED_SOFTWARE,
                                ThreatType.SOCIAL_ENGINEERING))
                        .allowScan(true)
                        .build();

                Gson gson = new Gson();

                try {
                    String respond = httpClientService.sendPostRequest(googleCloud + "?key=" + apiKey, gson.toJson(phishingRequest));
                    final PhishingRespond phishingRespond = gson.fromJson(respond, PhishingRespond.class);
                    for (PhishingScore score : phishingRespond.getScores()) {
                        if (!(score.getConfidenceLevel().equals(ConfidenceLevel.CONFIDENCE_LEVEL_UNSPECIFIED.name()) ||
                                score.getConfidenceLevel().equals(ConfidenceLevel.SAFE.name()))) {
                            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("groźny link");
                        }
                    }

                } catch (IOException | InterruptedException e) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("groźny link");
                }
            }
        }

        smsQueueService.enqueue(smsMessage);
        return ResponseEntity.ok("SMS received successfully from " + smsMessage.getSender());
    }

    // dodaje nowego abonenta banku do bazy czyli tablicy
    @PostMapping("/addsub")
    public ResponseEntity<String> addSubscriber(@RequestBody BankSubscriber bankSubscriber) {

        try {
            bankSubscriberService.add(bankSubscriber);
            return ResponseEntity.ok("Added a new the bank's subscriber " + bankSubscriber.getRecipient());
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
        }

    }

}
