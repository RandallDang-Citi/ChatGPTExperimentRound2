package org.example.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.example.jwtUtils.JWT;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
public class Demo {

    @PostMapping("/api/registration")
    public String generateJWT(@RequestBody Map body) {
        return JWT.createJavaWebToken(body, DateUtils.addMinutes(new Date(), 2));
    }

    @PostMapping("/api/claim")
    public String parseJWT(@RequestBody Map request) {
        if (CollectionUtils.isEmpty(request) && StringUtils.isEmpty((String)request.get("token"))) {
            return "request token is null" ;
        }
        Map<String, Object> payloadMap = JWT.parserJavaWebToken((String) request.get("token"));
        if(CollectionUtils.isEmpty(payloadMap)){
            return "token is invalid or expired , consider to regenerate a new one";
        }else {
            return JSON.toJSONString(payloadMap);
        }
    }
}
