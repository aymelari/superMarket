package org.example.supermarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SuperMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperMarketApplication.class, args);
    }

}
