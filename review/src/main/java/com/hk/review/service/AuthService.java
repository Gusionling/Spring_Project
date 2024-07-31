package com.hk.review.service;

import com.hk.review.api.request.SignUpAndSignInRequest;
import com.hk.review.contrant.Constrants;
import com.hk.review.exception.CommonException;
import com.hk.review.exception.enums.ErrorCode;
import com.hk.review.model.User;
import com.hk.review.model.dto.UserLoginDto;
import com.hk.review.model.dto.response.JwtTokenDto;
import com.hk.review.repository.UserRepository;
import com.hk.review.security.enums.ERole;
import com.hk.review.utility.JwtUtil;
import com.hk.review.utility.PasswordEncoder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public JwtTokenDto login(UserLoginDto userLoginDto) {
        User user;
        boolean isNewUser = false;

        Optional<User> existingUser = userRepository.findBySerialId(userLoginDto.providerId());

        if (existingUser.isPresent()) {
            user = existingUser.get();
        } else {
            user = userRepository.save(User.signUp(userLoginDto.providerId()));
            isNewUser = true;
            user.setIsNewUser(false);
            user.setPlatform(Constrants.PLATFORM_GENERAL);
        }

        JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(user.getId(), ERole.USER);

        if (isNewUser || !jwtTokenDto.refreshToken().equals(user.getRefreshToken())) {
            userRepository.updateRefreshTokenAndLoginStatus(user.getId(), jwtTokenDto.refreshToken(), true);
        }

        return jwtTokenDto;
    }

    @Transactional
    public JwtTokenDto signUp(SignUpAndSignInRequest request) {

        //전역변수로 두기 위해
        User user;

        // 해당 이메일로 가입된 적이 있는지 여부 확인
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if(existingUser.isPresent()){
            throw new CommonException(ErrorCode.EXIST_EMAIL);
        }

        Optional<User> where_user = userRepository.findBySerialId(request.getProviderId());

        if (where_user.isEmpty()) {
            if (request.getPlatform().equals(Constrants.PLATFORM_KAKAO)) {
                throw new CommonException(ErrorCode.NOT_FOUND_USER);
            }
            //일반 로그인인 경우
            else {
                user = userRepository.save(User.signUp(request.getProviderId()));
                user.setIsNewUser(false);
                user.setPlatform(Constrants.PLATFORM_GENERAL);

            }
        }else {
            // 카카오 로그인 사용자
            user = where_user.get();
        }

        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));


        JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(user.getId(), ERole.USER);

        user.setRefreshToken(jwtTokenDto.refreshToken());

        return jwtTokenDto;
    }

}
