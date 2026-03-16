package com.smartroutine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SmartRoutineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartRoutineApplication.class, args);
    }

}
