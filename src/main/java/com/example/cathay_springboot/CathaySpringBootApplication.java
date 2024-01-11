package com.example.cathay_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CathaySpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CathaySpringBootApplication.class, args);
    }

}
