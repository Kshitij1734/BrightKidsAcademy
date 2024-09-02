package com.brightkids_academy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve static resources from the "static" directory
        registry.addResourceHandler("/CSS/**")
                .addResourceLocations("classpath:/static/CSS/");
        
        registry.addResourceHandler("/JS/**")
        .addResourceLocations("classpath:/static/JS/");
        
        registry.addResourceHandler("/Images/**")
        .addResourceLocations("classpath:/static/Images/");
                
        // Serve uploaded images
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}
