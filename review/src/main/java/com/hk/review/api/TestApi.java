package com.hk.review.api;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestApi {

    @GetMapping("/hello/world")
    public String getHelloWorld(){
        return "[GET] Hello, World!";
    }

    @PostMapping("/hello/world")
    public String postHelloWorld(){
        return "[POST] Hello, World!";
    }

    @PutMapping("/hello/world")
    public String putHelloWorld(){
        return "[PUT] Hello, World!";
    }

    @DeleteMapping("/hello/world")
    public String deleteHelloWorld(){
        return "[DELETE] Hello, World!";
    }
}
