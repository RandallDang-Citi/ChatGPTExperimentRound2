package com.citi.copilot.controller;

import com.citi.copilot.entity.RegistrationEntity;
import com.citi.copilot.entity.Result;
import com.citi.copilot.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TokenController {


    @Autowired
    private TokenService tokenService;
    @PostMapping("/registration")
    public String registration(@RequestBody RegistrationEntity entity) {
        return tokenService.registration(entity);
    }

    @GetMapping("/claim")
    public Result claim(String token) {
        return tokenService.claim(token);
    }
}
