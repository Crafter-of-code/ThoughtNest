package com.ThoughtNest.BlogService.client;

import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {
    @Bean
    public RequestInterceptor basicAuthInterceptor(){
        return  new BasicAuthRequestInterceptor("User-Service","User-Service");
    }
}
