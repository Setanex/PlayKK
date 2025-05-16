package com.play.playkk.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
// odpowiedź z google cloud dot. zagrożenia
public class PhishingRespond {

    private List<PhishingScore> scores;
}
