package com.citi.cip.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Base64;
import java.util.Date;

public class TokenUtil {
    private static long tokenExpiration = 5*60*1000;
    private static String tokenSignKey = "TTS Tech China - THE BEST CSC";
    //帮我生成jws token
    public static String createToken(String subject, String secret)
    {

        String encodedKey = Base64.getEncoder().encodeToString(secret.getBytes()).hashCode()+"";

        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS256, encodedKey)
                .claim("applicationCsi", "175051")
                .claim("applicationName", "CIP")
                .compact();
    }
    //帮我解析token
    public static Claims parseToken(String secret, String token)
    {
        Claims body = null;
        try {
            String encodedKey = Base64.getEncoder().encodeToString(secret.getBytes()).hashCode()+"";
            body = Jwts.parser().setSigningKey(encodedKey).parseClaimsJws(token).getBody();
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            e.printStackTrace();
        }
        return body;
    }
}
