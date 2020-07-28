package com.sixkery.controller;

import com.sixkery.common.ApiResponse;
import com.sixkery.common.Status;
import com.sixkery.exception.SecurityException;
import com.sixkery.payload.LoginRequest;
import com.sixkery.util.JwtUtil;
import com.sixkery.vo.JwtResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 认证 Controller，包括用户注册，用户登录请求
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 登录
     */
    @PostMapping("/login")
    public ApiResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmailOrPhone(), loginRequest.getPassword()));

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        String jwt = jwtUtil.createJwt(authentication, loginRequest.getRememberMe());
        return ApiResponse.ofSuccess(new JwtResponse(jwt));
    }

    @PostMapping("/logout")
    public ApiResponse logout(HttpServletRequest request) {
        try {
            // 设置JWT过期
            jwtUtil.invalidateJwt(request);
        } catch (SecurityException e) {
            throw new SecurityException(Status.UNAUTHORIZED);
        }
        return ApiResponse.ofStatus(Status.LOGOUT);
    }
}
