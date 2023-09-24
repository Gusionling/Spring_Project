package com.hk.review.api;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestResponseApi {

    @GetMapping("/test/response/string")
    public String stringResponse(){
        return "This is String";
    }


    @GetMapping("/test/response/json")
    public TestResponseBody jsonResponse(){
        return new TestResponseBody("hk", 23);
    }

    @Getter
    public static class TestResponseBody{
        String name;
        Integer age;

        public TestResponseBody(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }
}
