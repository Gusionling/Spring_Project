package com.hk.review.service;

import com.hk.review.model.User;
import com.hk.review.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //private final JwtUtil jwtUtil; jwtUtil 클래스 구현 필요


    //파라미터로 User 엔티티를 받는다. 나중에 DTO 로 수정 필요하면 하기 회원가입이라서 필요없는 것 같기도
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
