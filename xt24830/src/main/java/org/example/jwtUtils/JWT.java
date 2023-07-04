package org.example.jwtUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;



public class JWT {

    private static final Logger log = LoggerFactory.getLogger(JWT.class);

    private static final String KEY = "TTS Tech China-THE BEST CSC";

    //该方法使用HS256算法和Secret:bankgl生成signKey
    private static Key getKeyInstance( ) {
        //We will sign our JavaWebToken with our ApiKey secret
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(getCustomKey());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName( ));
        return signingKey;
    }

    private static String getCustomKey() {
        return DatatypeConverter.printBase64Binary(KEY.getBytes());
    }

    //使用HS256签名算法和生成的signingKey最终的Token,claims 中是有效载荷
    public static String createJavaWebToken(Map< String, Object > claims, Date endtime) {
        return Jwts.builder( ).setClaims(claims).setExpiration(endtime).signWith(SignatureAlgorithm.HS256, getKeyInstance( )).compact( );
    }


    //解析Token，同时也能验证Token，当验证失败返回null
    public static Map< String, Object > parserJavaWebToken(String token) {
        try {
            Map< String, Object > jwtClaims = Jwts.parser( ).setSigningKey(getKeyInstance( )).parseClaimsJws(token).getBody( );
            return jwtClaims;
        } catch (Exception e) {
            //time out
            log.error("json web token verify failed,reason time out");
            return null;
        }
    }


}
