package com.poppang.api.poppanggoogle.service;

import com.poppang.api.poppanggoogle.dto.GoogleUserResponse;
import com.poppang.api.poppanggoogle.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {

    private final UserService userService;

    public GoogleUserResponse processUser(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        String googleId = oAuth2User.getAttribute("sub"); // Google에서 제공하는 고유 ID

        // Google UID 생성
        String uid = "google-" + googleId;

        // 3. DB 확인 (없으면 신규 생성)
        User user = userService.findUserByUid(uid)
                .orElseGet(() -> userService.createUser(uid, "google"));

        // 4. DTO로 변환해서 반환
        return new GoogleUserResponse(user.getUid(), email);
    }
}
