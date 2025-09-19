package com.poppang.api.poppanggoogle.service;

import com.poppang.api.poppanggoogle.entity.User;
import com.poppang.api.poppanggoogle.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 신규 생성
    public User createUser(String uid, String provider) {
        User newUser = User.builder()
                .uid(uid)
                .provider(User.Provider.valueOf(provider))  // enum 변환
                .role(User.Role.member)  // 기본값 설정 가능
                .build();
        return userRepository.save(newUser);
    }

    // UID 기반 조회
    public Optional<User> findUserByUid(String uid) {
        return userRepository.findByUid(uid);
    }

    // ✅ 추가: 닉네임 등 수정 후 저장
    public User saveUser(User user) {
        return userRepository.save(user);
    }

}