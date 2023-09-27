package com.hk.review.api;

import com.hk.review.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestEntityApi {

    private final TestService testService;

    @GetMapping("/test/entity/create")
    public void createTestEntity(){
        testService.create("hk", 23);

    }
}
