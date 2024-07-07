package com.hk.review.api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/health")
@Tag(name = "health 컨트롤러", description = "서버 상태 체크")
public class HealthCheckController {

    @Value("${server.env}")
    private String env;
    @Value("${server.port}")
    private String port;
    @Value("${server.address}")
    private String address;


    @GetMapping
    @Operation(summary = "서버 상태 체크", description = "서버의 상태를 체크합니다.")
    public ResponseEntity<?> healthCheck(){
        Map<String, String> responseData = new TreeMap<>();
        responseData.put("env", env);
        responseData.put("port", port);
        responseData.put("address", address);

        return ResponseEntity.ok(responseData);
    }

    @Operation(summary = "환경 변수 조회", description = "환경 변수를 조회합니다.")
    @GetMapping("/env")
    public ResponseEntity<String> getEnv(){

        return ResponseEntity.ok(env);
    }
}
