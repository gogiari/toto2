package com.example.toto.pds.configration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/Board/fileupload/**")
                .addResourceLocations("file:src/main/resources/fileupload/");
        registry.addResourceHandler("/ImageBoard/fileupload/**")
                .addResourceLocations("file:src/main/resources/fileupload/");
    }
}
