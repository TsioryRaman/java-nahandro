package com.training.breakfast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.training")
@EntityScan(basePackages = "com.training")
@EnableJpaRepositories(basePackages = "com.training")
public class BreakfastApplication {

    public static void main(String[] args) {
        SpringApplication.run(BreakfastApplication.class, args);
    }

}
