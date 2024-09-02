package com.FindMyPc.back.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Angular default URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow the HTTP methods you need
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true); // If you need to send cookies or headers with authentication information
    }
}
