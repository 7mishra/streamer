package com.streamer;

import com.auth.AuthController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreamerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthController.class, args);
    }
}
