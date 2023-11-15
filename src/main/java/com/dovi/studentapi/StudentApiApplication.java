package com.dovi.studentapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class StudentApiApplication {

    @Value("${address.service.url}")
    private String addressApiUrl;

    public static void main(String[] args) {
        SpringApplication.run(StudentApiApplication.class, args);
    }

    @Bean
    public WebClient addressWebClient() {
        WebClient webClient = WebClient.builder()
                .baseUrl(addressApiUrl)
                .build();

        return webClient;
    }

}
