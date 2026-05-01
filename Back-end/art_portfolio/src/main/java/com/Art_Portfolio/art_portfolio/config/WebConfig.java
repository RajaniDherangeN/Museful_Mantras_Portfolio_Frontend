package com.Art_Portfolio.art_portfolio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    private static final String IMAGE_UPLOAD_DIR = "D:/Digital paintings/website/Portfolio_Art/public/assets/image/"; // <-- your local folder

    @Bean
    public WebMvcConfigurer webConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:4200") // Angular dev server
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                // Serve static images at /Images/** URL path
                registry.addResourceHandler("/image/**")
                        .addResourceLocations("file:///" + IMAGE_UPLOAD_DIR)
                        .setCachePeriod(3600); // optional caching
            }
        };
    }
}
