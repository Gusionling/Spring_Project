package com.hk.review.api;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestRequestApi {

    // Request Parameter 방식
    @GetMapping("test/param")
    public String requestParam(
            @RequestParam String name,
            @RequestParam Integer age) {
        return "Hello, Request Param, I am " + name + ", " + age;
    }

    //Path Variable 방식
    @GetMapping("/test/path/{name}/{age}")
    public String requestPathVariable(
            @PathVariable String name,
            @PathVariable Integer age) {
        return "Hello, PathVariable, I am " + name + ", " + age;
    }

    //Request Body 방식
    @PostMapping("/test/body")
    public String requestBody(
            @RequestBody TestRequestBody request
    ){
        return "Hello, RequestBody :  I am " + request.name + ", " + request.age;
    }

    public static class TestRequestBody{
        String name;
        Integer age;

        public TestRequestBody(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }
}
