package com.citi.cip.service;

import com.citi.cip.util.TokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


public class DemoServiceTest {


    @Test
    public void test {
        //测试调用post请求
      String token =  TokenUtil.createToken("DemoToken","TTS Tech China - THE BEST CSC");
      System.out.println("====token==="+token);

    }
}
