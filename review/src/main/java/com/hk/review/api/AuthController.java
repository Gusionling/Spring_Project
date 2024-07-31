package com.hk.review.api;

import com.hk.review.api.request.SignUpAndSignInRequest;
import com.hk.review.contrant.Constrants;
import com.hk.review.model.dto.ResponseDto;
import com.hk.review.model.dto.UserLoginDto;
import com.hk.review.model.dto.response.JwtTokenDto;
import com.hk.review.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(Constrants.API_PREFIX + "/auth")
@Tag(name = "Auth", description = "인증 관련 API")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "PoviderId를 통해서 로그인을 한다.[테스트 용]")
    @Schema(name = "login", description = "로그인")
    public ResponseDto<?> login(@RequestBody UserLoginDto userloginDto) {
        return ResponseDto.ok(authService.login(userloginDto));
    }

    //새로운 providerID 아니면 그냥 기존으로 주자
    @Operation(summary = "카카오 로그인", description = "카카오 로그인 handler의 콜백 메서드이다.")
    @GetMapping("/oauth2/kakao")
    public ResponseEntity<JwtTokenDto> loginKakao(@RequestParam(name = "accessToken") String accessToken,
                                     @RequestParam(name = "refreshToken") String refreshToken) {
        return new ResponseEntity<>(JwtTokenDto.of(accessToken, refreshToken), HttpStatus.OK);
    }

    @Operation(
            summary = "회원가입",
            description = "회원가입 후 토큰을 반환합니다. 카카오 로그인인 경우는 카카오 인증을 먼저 받고 실행해주세요 platform은 kakao혹은 general을 입력해주세요"
    )
    @PostMapping("/sign-up")
    public ResponseDto<?> signUp(@RequestBody SignUpAndSignInRequest request) {

        return ResponseDto.ok(authService.signUp(request));

    }

}
