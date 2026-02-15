package com.aiintelli.aitoolsummarygenerator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC configuration to register performance interceptor.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final PerformanceInterceptor performanceInterceptor;

    public WebConfig(PerformanceInterceptor performanceInterceptor) {
        this.performanceInterceptor = performanceInterceptor;
    }

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        registry.addInterceptor(performanceInterceptor)
                .addPathPatterns("/api/**"); // Only monitor API endpoints
    }
}
