package com.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {
        "com.common.models",
})
public class AuthApplication {

    public static void main(String[] args) {
        System.out.println("Application up and running");
        SpringApplication.run(AuthApplication.class, args);
    }
}
