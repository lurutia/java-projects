package com.gosuljo.api.hello;

import com.gosuljo.common.entity.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {
    @GetMapping
    public String hello() {
        Member member = new Member();
        member.setId(1);
        member.setName("Hello~!");
        return member.toString();
    }
}
