package com.itsue.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String userId = request.getParameter("userId");
        String userPw = request.getParameter("userPw");

        session.removeAttribute("userId");
        session.removeAttribute("requiredUserId");
        session.removeAttribute("requiredUserPw");
        session.removeAttribute("global");

        session.setAttribute("userId", userId);


        try {
            if (userId == null || userId.isBlank()) {
                throw new LoginValidationException("requiredUserId", "NotBlank.userId"); // validations property setting!
            }

            if (userPw == null || userPw.isBlank()) {
                throw new LoginValidationException("requiredUserPw", "NotBlank.userPw"); // validations property setting!
            }

            throw new LoginValidationException("global", "Validation.login.fail"); // validations property setting!
        } catch (LoginValidationException e){
            session.setAttribute(e.getField(), e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/member/login"); // 실패시 로그인 페이지 이동
    }
}