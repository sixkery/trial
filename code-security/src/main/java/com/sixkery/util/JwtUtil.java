package com.sixkery.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.sixkery.common.Consts;
import com.sixkery.common.Status;
import com.sixkery.config.JwtConfig;
import com.sixkery.exception.SecurityException;
import com.sixkery.vo.UserPrincipal;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * JWT 工具类，主要功能：生成 JWT 并存入 Redis，解析 JWT 并校验正确性，从 Request 中获取 JWT
 *
 * @author liugang
 * @date 2020/7/16
 */
@EnableConfigurationProperties(JwtConfig.class)
@Configuration
@Slf4j
public class JwtUtil {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 创建 JWT
     *
     * @param rememberMe  记住我
     * @param id          用户 ID
     * @param subject     用户名
     * @param roles       用户角色
     * @param authorities 用户权限
     * @return JWT
     */
    public String createJwt(Boolean rememberMe, Long id, String subject, List<String> roles,
                            Collection<? extends GrantedAuthority> authorities) {
        Date date = new Date();
        JwtBuilder builder = Jwts.builder()
                .setId(id.toString())
                .setSubject(subject)
                .setIssuedAt(date)
                .signWith(SignatureAlgorithm.ES256, jwtConfig.getKey())
                .claim("roles", roles)
                .claim("authorities", authorities);

        // 设置过期时间
        Long expired = rememberMe ? jwtConfig.getRemember() : jwtConfig.getExpired();
        if (expired > 0) {
            builder.setExpiration(DateUtil.offsetMillisecond(date, expired.intValue()));
        }
        String jwt = builder.compact();
        // 将生成的JWT保存至Redis
        stringRedisTemplate.opsForValue()
                .set(Consts.REDIS_JWT_KEY_PREFIX + subject, jwt, expired, TimeUnit.MILLISECONDS);
        return jwt;
    }

    /**
     * 创建JWT
     *
     * @param authentication 用户认证信息
     * @param rememberMe     记住我
     * @return JWT
     */
    public String createJwt(Authentication authentication, Boolean rememberMe) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        return createJwt(rememberMe, userPrincipal.getId(), userPrincipal.getUsername(),
                userPrincipal.getRoles(), userPrincipal.getAuthorities());
    }

    /**
     * 解析 JWT
     *
     * @param jwt jwt
     * @return Claims
     */
    public Claims parseJwt(String jwt) {
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtConfig.getKey()).parseClaimsJws(jwt).getBody();
            String username = claims.getSubject();
            String redisKey = Consts.REDIS_JWT_KEY_PREFIX + username;

            // 校验 redis 中的 key 是否存在
            Long expire = stringRedisTemplate.getExpire(redisKey, TimeUnit.MICROSECONDS);
            if (Objects.isNull(expire) || expire < 0) {
                throw new SecurityException(Status.TOKEN_EXPIRED);
            }

            // 校验 redis 中的 jwt 是否与当前的一致，不一致代表用户已注销/用户在不同的设备上登录，均表示JWT 已过期
            String redisToken = stringRedisTemplate.opsForValue().get(redisKey);
            if (!StrUtil.equals(jwt, redisToken)) {
                throw new SecurityException(Status.TOKEN_OUT_OF_CTRL);
            }
            return claims;
        } catch (ExpiredJwtException e) {
            log.error("token 已过期");
            throw new SecurityException(Status.TOKEN_EXPIRED);
        } catch (UnsupportedJwtException e) {
            log.error("不支持的 Token");
            throw new SecurityException(Status.TOKEN_PARSE_ERROR);
        } catch (MalformedJwtException e) {
            log.error("Token 无效");
            throw new SecurityException(Status.TOKEN_PARSE_ERROR);
        } catch (SignatureException e) {
            log.error("无效的 Token 签名");
            throw new SecurityException(Status.TOKEN_PARSE_ERROR);
        } catch (IllegalArgumentException e) {
            log.error("Token 参数不存在");
            throw new SecurityException(Status.TOKEN_PARSE_ERROR);
        }

    }

    /**
     * 设置JWT过期
     *
     * @param request 请求
     */
    public void invalidateJwt(HttpServletRequest request) {

        String jwt = getJwtFromRequest(request);
        String username = getUsernameFromJwt(jwt);

        // 从redis中清除JWT
        stringRedisTemplate.delete(Consts.REDIS_JWT_KEY_PREFIX + username);
    }

    /**
     * 根据 jwt 获取用户名
     *
     * @param jwt JWT
     * @return 用户名
     */
    public String getUsernameFromJwt(String jwt) {
        Claims claims = parseJwt(jwt);
        return claims.getSubject();
    }

    /**
     * 从 request 的 header 中获取 JWT
     *
     * @param request 请求
     * @return JWT
     */
    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StrUtil.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
