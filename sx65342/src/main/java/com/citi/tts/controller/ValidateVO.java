package com.citi.tts.controller;

import lombok.Data;

@Data
public class ValidateVO {
    private String token;

    // JWT token secret key: HASH value of the base64 encoded string of the "TTS Tech China-THE BEST CSC" + "TTS Tech China-THE BEST CSC"
    // JWT token payload: applicationCsi
    // JWT token algorithm: HMAC256
    // JWT token audience: applicationCsi
    // JWT token issuer: TTS Tech China-THE BEST CSC
    // JWT token expire time: 5 minutes
    // JWT token issue time: current time
}
