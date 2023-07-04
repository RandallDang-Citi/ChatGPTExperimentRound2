package com.citi.cip.service;

import com.citi.cip.util.TokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class DemoService {


    //帮我生成一个返回JSONObject的方法

     public JSONObject getToken(JSONObject param) {

        JSONObject jsonObject = new JSONObject();
        String token = TokenUtil.createToken("demoToken","TTS Tech China - THE BEST CSC");
         JSONObject obj = new JSONObject();
         obj.put("applicationCsi",param.get("applicationCsi"));
         obj.put("applicationName",param.get("applicationName"));
         obj.put("secret", token);
         jsonObject.put("token",obj);
        jsonObject.put("status","success");
        return jsonObject;

     }
    public JSONObject checkToken(String token){
        JSONObject jsonObject = new JSONObject();

        //帮我写try catch
        Claims body = TokenUtil.parseToken("TTS Tech China - THE BEST CSC",token);
        //帮我生成token的jsonobject
        JSONObject obj = new JSONObject();
        obj.put("applicationCsi",body.get("applicationCsi"));
        obj.put("applicationName",body.get("applicationName"));
        obj.put("secret", token);
        jsonObject.put("token",obj);
        jsonObject.put("CSI", body.get("applicationCsi"));
        jsonObject.put("NAME",body.get("applicationName"));
        jsonObject.put("status","success");
        return jsonObject;

    }
    //JWT token secret key: HASH value of the base64 encoded string of "TTS Tech China - THE BEST CSC"


}
