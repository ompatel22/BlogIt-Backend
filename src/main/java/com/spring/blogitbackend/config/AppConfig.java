package com.spring.blogitbackend.config;

import com.cloudinary.Cloudinary;
import io.github.cdimascio.dotenv.Dotenv;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfig {

    private static final Dotenv dotenv = Dotenv.load();

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
    @Bean
    public Cloudinary getCloudinary() {
        Map config= new HashMap();
        config.put("cloud_name", dotenv.get("CLOUD_NAME"));
        config.put("api_key", dotenv.get("API_KEY"));
        config.put("api_secret", dotenv.get("API_SECRET"));
        return new Cloudinary(config);
    }

    @Bean
    public Dotenv dotenv() {
        return Dotenv.load(); // Loads .env file automatically
    }
}
