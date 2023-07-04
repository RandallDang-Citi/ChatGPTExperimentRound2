package com.citi.cip.controller;

import com.citi.cip.service.DemoService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController


public class DemoController {


    @Autowired
    DemoService demoService;
    //写一个GetMapping
    //返回一个字符串
    @PostMapping("/api/generation")
    @ResponseBody
    public JSONObject gettoken(@RequestBody JSONObject params){
        System.out.println("====params==="+params);

       JSONObject json =  demoService.getToken(params);
       System.out.println("====json==="+json);
       return json;
    }

    @GetMapping("/api/claim")
    @ResponseBody
    public JSONObject checktoken(@RequestParam String token){
        System.out.println("====params==="+token);

        JSONObject json =  demoService.checkToken(token);
        System.out.println("===check=json==="+json);
        return json;
    }

}
