package com.aiintelli.aitoolsummarygenerator.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

/**
 * Performance monitoring interceptor to track API request/response times.
 * Logs slow endpoints (> 1 second) for performance diagnostics.
 */
@Component
public class PerformanceInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(PerformanceInterceptor.class);
    private static final String REQUEST_START_TIME = "requestStartTime";
    private static final String REQUEST_ID = "requestId";
    private static final long SLOW_REQUEST_THRESHOLD_MS = 1000;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestId = UUID.randomUUID().toString().substring(0, 8);
        request.setAttribute(REQUEST_ID, requestId);
        request.setAttribute(REQUEST_START_TIME, System.currentTimeMillis());

        logger.debug("[{}] {} {}", requestId, request.getMethod(), request.getRequestURI());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) {
        Long startTime = (Long) request.getAttribute(REQUEST_START_TIME);
        String requestId = (String) request.getAttribute(REQUEST_ID);

        if (startTime != null) {
            long duration = System.currentTimeMillis() - startTime;
            String method = request.getMethod();
            String uri = request.getRequestURI();
            int status = response.getStatus();

            if (duration > SLOW_REQUEST_THRESHOLD_MS) {
                logger.warn("⚠️ SLOW REQUEST [{}] {} {} - {}ms (status: {})",
                        requestId, method, uri, duration, status);
            } else {
                logger.info("✅ [{}] {} {} - {}ms (status: {})",
                        requestId, method, uri, duration, status);
            }
        }
    }
}
