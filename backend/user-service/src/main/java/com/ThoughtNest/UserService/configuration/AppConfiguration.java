package com.ThoughtNest.UserService.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfiguration {
    @Value("${cloudinary.cloud-name}")
    private String cloudName;
    @Value("${cloudinary.api-key}")
    private String apiKey;
    @Value("${cloudinary.api-secret}")
    private String apiSecret;
    @Bean
    public Cloudinary cloudinaryConfig(){
        Map<String,String> config  = new HashMap<>();
        config.put("cloud_name",cloudName);
        config.put("api_key",apiKey);
        config.put("api_secret",apiSecret);
        return new Cloudinary(config);
    }
}
