package com.naher_farhsa.ATMSpringboot;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ATMConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
