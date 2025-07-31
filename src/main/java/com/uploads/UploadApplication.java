package com.uploads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {
        "com.uploads",
        "com.common",
})
@EntityScan(basePackages = {
        "com.uploads",
        "com.common",
})
@EnableJpaRepositories(basePackages = "com.common.models")
public class UploadApplication {

    public static void main(String[] args) {
        System.out.println("Upload Application up and running");
        SpringApplication.run(com.uploads.UploadApplication.class, args);
    }
}

