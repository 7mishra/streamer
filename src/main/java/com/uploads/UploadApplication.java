package com.uploads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class UploadApplication {

    public static void main(String[] args) {
        System.out.println("Application up and running");
        SpringApplication.run(com.uploads.UploadApplication.class, args);
    }
}

