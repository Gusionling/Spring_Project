package com.hk.review.oauth.service;

import com.hk.review.model.User;
import com.hk.review.oauth.info.KakaoMemberDetails;
import com.hk.review.oauth.info.KakaoUserInfo;
import com.hk.review.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoMemberDetailService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        KakaoUserInfo kakaoUserInfo = new KakaoUserInfo(oAuth2User.getAttributes());

        User user = userRepository.findBySerialId(kakaoUserInfo.getId())
                .orElseGet(() ->
                        userRepository.save(User.builder()
                                .serialId(kakaoUserInfo.getId())
                                //카카오 이메일이기 때문에 보류
                                //.email(kakaoUserInfo.getEmail())
                                .build())
                );
        return KakaoMemberDetails.createByProviderId(user.getSerialId(), oAuth2User);
    }

}
