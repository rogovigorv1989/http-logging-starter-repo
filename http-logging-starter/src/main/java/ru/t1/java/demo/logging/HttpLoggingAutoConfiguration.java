package ru.t1.java.demo.logging;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "logging.http.enabled", havingValue = "true", matchIfMissing = true)
public class HttpLoggingAutoConfiguration {
    @Bean
    public HttpLoggingAspect httpLoggingAspect() {
        return new HttpLoggingAspect();
    }
}