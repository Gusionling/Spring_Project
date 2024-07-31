package com.hk.review.api.response;

import com.hk.review.model.dto.response.JwtTokenDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
public class KakaoLoginResponse {

    private String accessToken;
    private String refreshToken;
    private Long providerId;

    public static KakaoLoginResponse of(String accessToken,String refreshToken, Long providerId) {
        return KakaoLoginResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .providerId(providerId)
            .build();
    }

}
