package com.sparta.clonecodingunicorn.global.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                /**
                 * setConnectTimeout: 설정된 시간 동안 서버와의 연결이 이루어지지 않으면 연결 시간 초과 예외가 발생함
                 * setReadTimeout: 설정된 시간 동안 서버로부터 데이터를 수신하지 못하면 읽기 시간 초과 예외가 발생함
                 */
                .setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(5))
                .build();
    }
}