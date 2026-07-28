package com.ThoughtNest.UserService.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfiguration {
    @Bean
    public RestClient provideRestClient(){
        return  RestClient.create();
    }
}
