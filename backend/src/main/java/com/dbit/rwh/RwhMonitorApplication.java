package com.dbit.rwh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RwhMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(RwhMonitorApplication.class, args);
    }
}
