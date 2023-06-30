package com.citi.tts.controller;

import lombok.Data;

@Data
public class RegisterVO {

    private String applicationCsi;

    private String applicationName;

//         token = JWT.create().withAudience(user.getId()).sign(Algorithm.HMAC256(user.getPassword()));
    // 保存 token 到 redis
    // redisTemplate.opsForValue().set(user.getId(), token);

    // 返回 token
    // return CommonResponse.ok().data(token);
}
