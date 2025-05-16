package com.play.playkk.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
// model sms
public class SmsMessageIn {

    private String sender;
    private String recipient;
    private String message;
}
