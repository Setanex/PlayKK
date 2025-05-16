package com.play.playkk.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
// żądanie wysyłyne do google cloud aby sprawdzić zagrożenie
public class PhishingRequest {

    private String uri;
    private List<ThreatType> threatTypes;
    private boolean allowScan;
}
