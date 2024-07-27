package com.hk.review.security.service;

import com.hk.review.exception.CommonException;
import com.hk.review.exception.enums.ErrorCode;
import com.hk.review.repository.UserRepository;
import com.hk.review.security.info.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailService {

    private final UserRepository userRepository;

    public UserDetails loadUserById(Long id) {
        UserRepository.UserSecurityForm userSecurityForm = userRepository.findSecurityFormById(id)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_LOGIN_USER));

        return UserPrincipal.createByUserSecurityForm(userSecurityForm);
    }

}
