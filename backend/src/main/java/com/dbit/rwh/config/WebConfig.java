package com.dbit.rwh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Web MVC Configuration to serve static frontend assets from the separate frontend/ directory.
 * This enables seamless full-stack execution when running Spring Boot directly from IntelliJ IDEA or CLI.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Resolve frontend folder location dynamically with trailing slash
        Path parentFrontend = Paths.get("..", "frontend").toAbsolutePath().normalize();
        Path localFrontend = Paths.get("frontend").toAbsolutePath().normalize();

        String parentUri = parentFrontend.toUri().toString();
        if (!parentUri.endsWith("/")) parentUri += "/";

        String localUri = localFrontend.toUri().toString();
        if (!localUri.endsWith("/")) localUri += "/";

        registry.addResourceHandler("/**")
                .addResourceLocations(parentUri, localUri, "classpath:/static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Forward root URL to index.html
        registry.addViewController("/").setViewName("forward:/index.html");
    }
}
