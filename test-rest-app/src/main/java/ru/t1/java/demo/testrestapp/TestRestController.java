package ru.t1.java.demo.testrestapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    @GetMapping("/test")
    public String hello(@RequestParam String value) {
        return "You entered a value: " + value;
    }
}
