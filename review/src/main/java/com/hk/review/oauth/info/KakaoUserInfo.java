package com.hk.review.oauth.info;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hk.review.contrant.Constrants;

import java.util.Map;

import static com.hk.review.contrant.Constrants.KAKAO_ACCOUNT;

public class KakaoUserInfo {

    private Map<String, Object> attributes;

    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Long getId(){
        return Long.parseLong((String) attributes.get(Constrants.KAKAO_ID));
    }

    public String getEmail() {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<Map<String, Object>> typeReferencer = new TypeReference<Map<String, Object>>() {
        };

        Object kakaoAccount = attributes.get(KAKAO_ACCOUNT);
        Map<String, Object> account = objectMapper.convertValue(kakaoAccount, typeReferencer);

        return (String) account.get(Constrants.KAKAO_EMAIL);
    }
}
