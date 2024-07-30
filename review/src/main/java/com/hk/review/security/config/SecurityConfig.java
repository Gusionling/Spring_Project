package com.hk.review.security.config;

import com.hk.review.contrant.Constrants;
import com.hk.review.oauth.service.KakaoMemberDetailService;
import com.hk.review.security.filter.GlobalLoggerFilter;
import com.hk.review.security.filter.JwtAuthenticationFilter;
import com.hk.review.security.filter.JwtExceptionFilter;
import com.hk.review.security.handler.jwt.JwtAuthEntryPoint;
import com.hk.review.security.handler.oauth.OAuth2AuthenticationSuccessHandler;
import com.hk.review.security.handler.oauth.OAuth2FailureHandler;
import com.hk.review.security.handler.singout.CustomLogoutResultHandler;
import com.hk.review.security.handler.singout.CustomSignOutProcessHandler;
import com.hk.review.security.service.CustomUserDetailService;
import com.hk.review.utility.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomSignOutProcessHandler customSignOutProcessHandler;
    private final CustomLogoutResultHandler customSignOutResultHandler;
    private final JwtAuthEntryPoint jwtAuthEntryPoint;
    private final CustomUserDetailService customUserDetailService;
    private final JwtUtil jwtUtil;
    private final KakaoMemberDetailService KakaoMemberDetailService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2FailureHandler oAuth2FailureHandler;

    @Bean
    protected SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(registry ->
                        registry
                                .requestMatchers(Constrants.NO_NEED_AUTH_URLS.toArray(String[]::new)).permitAll()
                                .anyRequest().authenticated()

                )
                .logout(configurer ->
                        configurer
                                .logoutUrl("/api/v1/auth/sign-out")
                                .addLogoutHandler(customSignOutProcessHandler)
                                .logoutSuccessHandler(customSignOutResultHandler)
                )
                .exceptionHandling(configurer ->
                        configurer
                                .authenticationEntryPoint(jwtAuthEntryPoint)
                )
                .oauth2Login(oAuth2Login ->{
                    oAuth2Login.userInfoEndpoint(userInfoEndpointConfig ->
                            userInfoEndpointConfig.userService(KakaoMemberDetailService)
                    );
                    oAuth2Login
                            .successHandler(oAuth2AuthenticationSuccessHandler)
                            .failureHandler(oAuth2FailureHandler);
                })

                .addFilterBefore(
                        new JwtAuthenticationFilter(jwtUtil, customUserDetailService),
                                LogoutFilter.class)
                .addFilterBefore(new JwtExceptionFilter(),
                        JwtAuthenticationFilter.class)
                .addFilterBefore(new GlobalLoggerFilter(),
                        JwtExceptionFilter.class)
                .getOrBuild();
    }


}
