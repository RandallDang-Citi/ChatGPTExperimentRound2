package org.citi.chatgpt.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.citi.chatgpt.model.ApplicationInfo;
import org.citi.chatgpt.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class Controller {

    public static final String slogan = "TTS Tech China - THE BEST CSC";
    public static final String encryKey = Base64.getEncoder().encodeToString(slogan.getBytes(StandardCharsets.UTF_8)).hashCode() + "";

    @PostMapping("/api/registration")
    public String registration(
            @RequestBody ApplicationInfo applicationInfo) {

        Map<String, Object> claim = new HashMap<>();
        claim.put("applicationCsi", applicationInfo.getApplicationCsi());
        claim.put("applicationName", applicationInfo.getApplicationName());

        long currentTime = System.currentTimeMillis();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(currentTime))
                .setSubject("system")
                .setIssuer("Citi")
                .setAudience("CitiApplication")
                .signWith(SignatureAlgorithm.HS256, encryKey)
                .setExpiration(new Date(currentTime + 300 * 1000))
                .addClaims(claim)
                .compact();
    }


    @GetMapping("/api/claim")
    public ResponseEntity<Response> claim(
            @RequestBody String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(encryKey).parseClaimsJws(token).getBody();
            if (claims.getExpiration().before(new Date())) {
                return ResponseEntity.badRequest().body(Response.builder().token(token).status("EXPIRED").build());
            }

            return ResponseEntity.ok(Response.builder().token(token).status("SUCCESS").build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Response.builder().token(token).status("FAILED").build());
        }

    }

}



