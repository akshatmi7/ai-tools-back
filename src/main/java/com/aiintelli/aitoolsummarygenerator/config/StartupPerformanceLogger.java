package com.aiintelli.aitoolsummarygenerator.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;

/**
 * Logs application startup performance metrics.
 * Helps identify slow startup issues in production.
 */
@Component
public class StartupPerformanceLogger {

    private static final Logger logger = LoggerFactory.getLogger(StartupPerformanceLogger.class);
    private final long jvmStartTime;

    public StartupPerformanceLogger() {
        this.jvmStartTime = ManagementFactory.getRuntimeMXBean().getStartTime();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void logStartupTime() {
        long startupDuration = System.currentTimeMillis() - jvmStartTime;

        logger.info("╔════════════════════════════════════════════════════════════╗");
        logger.info("║  🚀 APPLICATION STARTUP COMPLETE                           ║");
        logger.info("║  ⏱️  Total startup time: {} ms                      ║",
                String.format("%,d", startupDuration));
        logger.info("║  ✅ Application is ready to serve requests                 ║");
        logger.info("╚════════════════════════════════════════════════════════════╝");

        if (startupDuration > 30000) {
            logger.warn("⚠️ Startup took longer than expected ({}ms). Consider optimizing initialization.",
                    startupDuration);
        }
    }
}
