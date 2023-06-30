package com.citi.tts.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {


    @GetMapping(value = "test")
    @ResponseBody
    public CommonResponse test4() {
        return CommonResponse.ok();
    }

   @PostMapping(value = "api/registration")
   @ResponseBody
    public CommonResponse register(@RequestBody RegisterVO registerVO) {


        return CommonResponse.ok(AppJwtUtil.getToken(registerVO.getApplicationCsi(),registerVO.getApplicationName()));
    }

    @PostMapping(value = "api/claim")
    @ResponseBody
    public CommonResponse register(@RequestBody ValidateVO validateVO) {

        String token = validateVO.getToken();

        Claims claimsBody = AppJwtUtil.getClaimsBody(token);

        int result = AppJwtUtil.verifyToken(claimsBody);
        if (result == -1) {
            return CommonResponse.ok();

        } else {
            return CommonResponse.fail("fail", "fail");


        }


    }}
