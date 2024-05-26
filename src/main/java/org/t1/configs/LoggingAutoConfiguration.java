package org.t1.configs;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.t1.interceptors.LoggingInterceptor;
import org.t1.services.LoggingService;

/**
 * Конфигурация для автоматической настройки логирования.
 */
@Configuration
@ConditionalOnWebApplication
public class LoggingAutoConfiguration {

    @Bean
    public LoggingInterceptor loggingInterceptor() {
        return new LoggingInterceptor();
    }

    @Bean
    public LoggingInterceptorConfig loggingInterceptorConfig() {
        return new LoggingInterceptorConfig();
    }

    @Bean
    public LoggingService loggingService() {
        return new LoggingService();
    }
}