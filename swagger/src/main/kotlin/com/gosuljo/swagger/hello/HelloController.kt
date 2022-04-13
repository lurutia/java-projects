package com.gosuljo.swagger.hello

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class HelloController {
    @GetMapping("/hello")
    fun getHelloWorld(): ResponseEntity<String> {
        return ResponseEntity.ok("HelloWorld!")
    }
}