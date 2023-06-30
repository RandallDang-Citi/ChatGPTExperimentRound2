package com.citi.copilot.service;

import com.citi.copilot.config.SecretKey;
import com.citi.copilot.entity.RegistrationEntity;
import com.citi.copilot.entity.Result;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    @Autowired
    private SecretKey secretKey;

    private Long expireTime = 1000 * 60 * 5L;

    public String registration(RegistrationEntity entity) {

        // 1. 生成一个JWT header
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");

        // 2. 生成一个JWT payload
        // 2.1 生成一个JWT payload
        Map<String, Object> payload = new HashMap<>();
        payload.put("applicationCsi", entity.getApplicationCsi());
        payload.put("applicationName", entity.getApplicationName());


        // 3. 生成一个JWT signature
        // 3.1 生成一个JWT signature
        String signature = secretKey.getSecretKey();

        // 4. 生成一个JWT
        // 使用JwtBuilder生成一个JWT
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setHeader(header);
        jwtBuilder.setClaims(payload);
        jwtBuilder.signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, signature);

        jwtBuilder.setExpiration(new java.util.Date(System.currentTimeMillis() + expireTime));

        return jwtBuilder.compact();



    }

    public Result claim(String token) {
        // 1. 解析JWT
        // 1.1 解析JWT
        try {
            io.jsonwebtoken.Claims claims = Jwts.parser()
                    .setSigningKey(secretKey.getSecretKey())
                    .parseClaimsJws(token)
                    .getBody();
            // 1.2 获取JWT payload
            RegistrationEntity entity = new RegistrationEntity();
            entity.setApplicationCsi((String) claims.get("applicationCsi"));
            entity.setApplicationName((String) claims.get("applicationName"));
            // 构造Result
            Result result = new Result();
            result.setToken(entity);
            result.setStatus("success");
            return result;
        }catch (ExpiredJwtException e){
            Result result = new Result();
            result.setStatus("expired");
            result.setToken(null);
            return result;
        }catch (Exception e){
            Result result = new Result();
            result.setStatus("fail");
            result.setToken(null);
            return result;
        }


    }
}
