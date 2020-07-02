package com.sixkery.shiro;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sixkery.util.JwtUtils;
import com.sixkery.util.ResponseModel;
import com.sixkery.util.ResponseModels;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sixkery
 * @date 2020/6/13
 */
@Component
public class JwtFilter extends AuthenticatingFilter {

    @Autowired
    JwtUtils jwtUtils;

    // 实现登录的逻辑
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authentication = httpServletRequest.getHeader("Authentication");
        if (StringUtils.isEmpty(authentication)) {
            return null;
        }
        return new JwtToken(authentication);
    }

    // 拦截校验
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authentication = httpServletRequest.getHeader("Authentication");
        if (StringUtils.isEmpty(authentication)) {
            return true;
        } else {
            // 校验
            Claims claims = jwtUtils.getClaimByToken(authentication);
            boolean tokenExpired = jwtUtils.isTokenExpired(claims.getExpiration());
            if (claims == null || tokenExpired) {
                throw new ExpiredCredentialsException("token失效,请重新登录!");
            }
            // 执行登录
            return executeLogin(request, response);
        }
    }


    @SneakyThrows
    @Override
    protected boolean onLoginFailure(AuthenticationToken token,
                                     AuthenticationException e,
                                     ServletRequest request, ServletResponse response) {
        Throwable throwable = e.getCause() == null ? e : e.getCause();
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        ResponseModel<Object> failed = ResponseModels.failed(throwable.getMessage());
        String str = JSONUtil.toJsonStr(failed);
        httpServletResponse.getWriter().println(str);
        return false;

    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(org.springframework.http.HttpStatus.OK.value());
            return false;
        }

        return super.preHandle(request, response);
    }
}
