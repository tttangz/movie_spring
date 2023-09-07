package com.auth.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ruoyi.common.core.exception.GlobalException;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    //密钥
    @Value("${wx-auth.jwt.secret}")
    private String secret;

    //过期时间（天）
    @Value("${wx-auth.jwt.expire}")
    private int expire;

    @Value("${wx-auth.jwt.refreshExpire}")
    private int refreshExpire;

    public String createToken(int userId) {
        Algorithm algorithm = Algorithm.HMAC256(secret); //创建加密算法对象
        JWTCreator.Builder builder = JWT.create().withClaim("userId", userId);
        Date date = new Date();
        date = DateUtils.addDays(date, expire);
        return builder.withExpiresAt(date).sign(algorithm);
    }

    public String createRefreshToken(int userId) {
        Algorithm algorithm = Algorithm.HMAC256(secret); //创建加密算法对象
        JWTCreator.Builder builder = JWT.create().withClaim("userId", userId);
        Date date = new Date();
        date = DateUtils.addDays(date, refreshExpire);
        return builder.withExpiresAt(date).sign(algorithm);
    }


    public int getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asInt();
        } catch (Exception e) {
            throw new GlobalException("令牌无效");
        }
    }

    public void verifierToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret); //创建加密算法对象
        JWTVerifier verifier = JWT.require(algorithm).build();
        verifier.verify(token);
    }
}

