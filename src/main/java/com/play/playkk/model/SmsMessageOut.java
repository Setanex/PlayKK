package com.play.playkk.model;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
// zaakceptowany sms wysyłany do operatora tel. z początku kolejki
public class SmsMessageOut {

    private String id;

    private List<SmsMessageIn> messages;

}
