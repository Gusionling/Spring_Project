package com.hk.review.common.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hk.review.contrant.Constrants;
import com.hk.review.intercepter.pre.UserIdArgumentResolver;
import com.hk.review.intercepter.pre.UserIdInterceptor;
import com.hk.review.security.service.CustomUserDetailService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final UserIdArgumentResolver userIdArgumentResolver;
    private final CustomUserDetailService customUserDetailService;

    @Override
    public void addArgumentResolvers(@NonNull List<HandlerMethodArgumentResolver> resolvers) {
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
        resolvers.add(this.userIdArgumentResolver);
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new UserIdInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(Constrants.NO_NEED_AUTH_URLS);
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
