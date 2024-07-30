package com.hk.review.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestKakaoController {

    //인가 코드를 사용해 액세스 토큰 요청
   /* @GetMapping("/oauth2/kakao")
    public ResponseEntity<String> loginKakao(@RequestParam(name = "code") String code) {
        // 인가 코드를 사용해 액세스 토큰 요청
        log.info("code: {}", code);
        return new ResponseEntity<>("인증 처리 과정", HttpStatus.OK);
    }*/
}
