package com.hk.review.security.handler.oauth;

import com.hk.review.contrant.Constrants;
import com.hk.review.model.dto.response.JwtTokenDto;
import com.hk.review.oauth.info.KakaoUserInfo;
import com.hk.review.security.enums.ERole;
import com.hk.review.security.service.CustomUserDetailService;
import com.hk.review.utility.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
//이 클래스는 인증이 성공적으로 완료 되었을 때 실행이 되는 친구이다. 그렇기 때문에 인증이 안되면 여기까지 오지 못한다.
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final String REDIRECT_URL = "http://localhost:8080/api/v1/auth/oauth2/kakao?accessToken=%s&refreshToken=%s";

    private final JwtUtil jwtUtil;
    private final CustomUserDetailService customUserDetailService;

    @Override

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        KakaoUserInfo kakaoUserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        System.out.println("OAuth2AuthenticationSuccessHandler: Authentication 들어옴");

        Long userId = ((Number) kakaoUserInfo.getAttributes().get(Constrants.KAKAO_ID)).longValue();

        //토큰 발급
        JwtTokenDto tokens = jwtUtil.generateTokens(userId, ERole.USER);

        String redirectUrI = String.format(REDIRECT_URL, tokens.accessToken(), tokens.refreshToken());
        getRedirectStrategy().sendRedirect(request, response, redirectUrI);

        System.out.println("OAuth2AuthenticationSuccessHandler: Authentication successful");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"accessToken\":\"" + tokens.accessToken() + "\", \"refreshToken\":\"" + tokens.refreshToken() + "\"}");

    }

}
