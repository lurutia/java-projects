package com.gosuljo.hellojib.hello_jib;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloJib {

    @GetMapping("/hello")
    public String helloJib() {
        return "Hello, Jib!";
    }
}
