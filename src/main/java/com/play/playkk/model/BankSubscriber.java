package com.play.playkk.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Bank_Subscriber")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
// model abonenta banku
public class BankSubscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique=true)
    private String recipient;

    private String startstop = StartStop.STOP.name();
}
