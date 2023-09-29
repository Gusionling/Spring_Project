package com.hk.review.api;

import com.hk.review.service.TestService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class TestEntityApi {

    private final TestService testService;

    @GetMapping("/test/entity/create")
    public void createTestEntity(
            @RequestBody CreateTestEntityRequest request
    ){
        testService.create(request.getName(), request.getAge());
    }

    @DeleteMapping("/test/entity/{id}")
    public void deleteTestEntity(
        @PathVariable Long id
    ){
        testService.delete(id);
    }

    @Getter
    @AllArgsConstructor
    public static class CreateTestEntityRequest{
        private final String name;
        private final Integer age;
    }
}
