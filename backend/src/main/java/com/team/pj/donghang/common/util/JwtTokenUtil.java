package com.team.pj.donghang.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
@NoArgsConstructor
public class JwtTokenUtil {
    private static String secretKey = "dyAeHubOOc8KaOfYB6XEQoEj1QzRlVgtjNL8PYs1A1tymZvvqkcEU7L1imkKHeDa";

    private static long expirationTime = 1000 * 60 * 60 * 24; // 1시간 TODO: 테스트 용으로 24시간임.

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";

    public static final String ISSUER = "donghang.com";

    public static JWTVerifier getVerifier() {
        return JWT.require(Algorithm.HMAC512(secretKey.getBytes()))
                .withIssuer(ISSUER)
                .build();
    }

    public static Date makeTokenExpiration(long expirationTime) {
        Date now = new Date();
        return new Date(now.getTime()+expirationTime);
    }

    public static String getToken(String userNoIntoString) {
        Date expires = JwtTokenUtil.makeTokenExpiration(expirationTime);

        return JWT.create()
                .withSubject(userNoIntoString)
                .withExpiresAt(expires)
                .withIssuer(ISSUER)
                .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .sign(Algorithm.HMAC512(secretKey.getBytes()));
    }

    public static void handleError(String token) {
        JWTVerifier verifier = JWT
                .require(Algorithm.HMAC512(secretKey.getBytes()))
                .withIssuer(ISSUER)
                .build();

        verifier.verify(token.replace(TOKEN_PREFIX, ""));
    }
}
