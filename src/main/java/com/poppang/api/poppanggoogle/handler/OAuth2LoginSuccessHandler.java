package com.poppang.api.poppanggoogle.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.poppang.api.poppanggoogle.dto.GoogleUserResponse;
import com.poppang.api.poppanggoogle.service.GoogleAuthService;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final GoogleAuthService googleAuthService;
    private final ObjectMapper objectMapper;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // 서비스 호출해서 DTO 생성
        GoogleUserResponse userResponse = googleAuthService.processUser(oAuth2User);

        // ✅ 콘솔 로그
        System.out.println("✅ 구글 로그인 성공!");
        System.out.println(" - User: " + userResponse);
        System.out.println(" - UID: " + userResponse.getUid());
        System.out.println(" - Email: " + userResponse.getEmail());
        System.out.println(" - Attributes: " + oAuth2User.getAttributes());

        // JSON 응답 반환
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(userResponse));
    }
}
