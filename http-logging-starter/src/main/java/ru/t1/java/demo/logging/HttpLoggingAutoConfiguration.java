package ru.t1.java.demo.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class HttpLoggingAutoConfiguration {
    private final Environment environment;

    @Autowired
    public HttpLoggingAutoConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public HttpLoggingConfig config() {
        String logLevel = environment.getProperty("logging.http.level");
        return new HttpLoggingConfig(logLevel);
    }

    @Bean
    @ConditionalOnProperty(name = "logging.http.enabled", havingValue = "true", matchIfMissing = true)
    public HttpLoggingAspect httpLoggingAspect(HttpLoggingConfig config) {
        return new HttpLoggingAspect(config);
    }
}