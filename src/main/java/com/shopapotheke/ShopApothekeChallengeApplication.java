package com.shopapotheke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ShopApothekeChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApothekeChallengeApplication.class, args);
    }

}
