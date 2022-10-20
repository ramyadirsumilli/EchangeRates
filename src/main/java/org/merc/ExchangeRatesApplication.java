package org.merc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAutoConfiguration
@EnableScheduling
@SpringBootApplication
public class ExchangeRatesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeRatesApplication.class, args);
    }

}
