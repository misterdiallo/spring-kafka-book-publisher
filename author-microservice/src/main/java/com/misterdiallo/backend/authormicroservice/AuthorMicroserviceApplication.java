package com.misterdiallo.backend.authormicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class AuthorMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorMicroserviceApplication.class, args);
    }

}
