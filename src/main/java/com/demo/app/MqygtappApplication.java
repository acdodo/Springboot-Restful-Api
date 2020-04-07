package com.demo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = { "classpath:cxf-res.xml" })
public class MqygtappApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqygtappApplication.class, args);
    }

}
