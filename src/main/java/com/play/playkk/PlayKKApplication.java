package com.play.playkk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.play.playkk"})
@EnableScheduling
public class PlayKKApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayKKApplication.class, args);
	}

}
