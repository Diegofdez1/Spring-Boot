package com.malaga42.demo.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RequestsLimitation {

    @Value("${limit.maximum.requests}")
    private int requests;

    @Value("${limit.maximum.requests.minutes}")
    private int minutes;

    @Bean
    public Bucket getBucket() {
        Bandwidth limit = Bandwidth.classic(requests, Refill.greedy(requests, Duration.ofMinutes(minutes)));
        return Bucket.builder()
                .addLimit(limit)
                .build();
    }
}



