package com.play.playkk.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
// wynik zagrożenia w odpowiedzi z google cloud
public class PhishingScore {

    private ThreatType threatType;
    private String confidenceLevel;
}
