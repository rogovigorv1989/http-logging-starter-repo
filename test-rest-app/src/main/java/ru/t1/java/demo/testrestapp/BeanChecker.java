package ru.t1.java.demo.testrestapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.t1.java.demo.logging.HttpLoggingAspect;

@Component
public class BeanChecker implements CommandLineRunner {

    private final HttpLoggingAspect httpLoggingAspect;

    @Autowired
    public BeanChecker(HttpLoggingAspect httpLoggingAspect) {
        this.httpLoggingAspect = httpLoggingAspect;
    }

    @Override
    public void run(String... args) {
        if (httpLoggingAspect == null) {
            System.out.println("😢 Аспект НЕ зарегистрирован как Bean!");
        } else {
            System.out.println("👍 Аспект зарегистрирован как Bean!");
        }
    }
}
