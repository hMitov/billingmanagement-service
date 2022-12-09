package com.billingmanagementservice.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableScheduling
@ComponentScan({"com.billingmanagementservice.domain", "com.billingmanagementservice.rest"})
@EnableJpaRepositories("com.billingmanagementservice.domain.repository")
@EntityScan("com.billingmanagementservice.domain.entity")
public class BillingmanagementServiceApplication {
    @Bean
    public WebClient getWebClientBuilder() {
        return WebClient.builder()
                .baseUrl("http://localhost:8082/api")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(BillingmanagementServiceApplication.class, args);
    }

}
