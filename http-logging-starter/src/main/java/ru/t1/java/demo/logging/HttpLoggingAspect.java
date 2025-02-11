package ru.t1.java.demo.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Aspect
@ConditionalOnProperty(name = "logging.http.enabled", havingValue = "true", matchIfMissing = true)
public class HttpLoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(HttpLoggingAspect.class);

    private final HttpLoggingConfig config;

    @Autowired
    public HttpLoggingAspect(HttpLoggingConfig config) {
        this.config = config;
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void logPointcut() {}

    @Around("logPointcut()")
    public Object logHttpRequests(ProceedingJoinPoint joinPoint) throws Throwable {
        String level = config.logLevel().toLowerCase();
        if (!isValidLogLevel(level)){
            level = "info";
        }

        log(level, "HTTP Request: " + joinPoint.getSignature().toShortString());
        Object result = joinPoint.proceed();
        log(level, "HTTP Response: " + result);
        return result;
    }

    private boolean isValidLogLevel(String level) {
        return level.equals("info") || level.equals("debug") || level.equals("warn") || level.equals("error");
    }

    private void log(String level, String message) {
        switch (level) {
            case "debug":
                log.debug(message);
                break;
            case "warn":
                log.warn(message);
                break;
            case "error":
                log.error(message);
                break;
            default:
                log.info(message);
        }
    }
}
