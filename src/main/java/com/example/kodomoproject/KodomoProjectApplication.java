package com.example.kodomoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class KodomoProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(KodomoProjectApplication.class, args);
    }

}
