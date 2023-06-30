package org.tailei.domain.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.tailei.adapter.util.JsonXmlConverter;
import org.tailei.domain.model.Token;
import org.tailei.domain.model.TokenValidateResult;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

@Component
public class TradeService {

    public static final String SECRET_KEY = "TTS Tech China - THE BEST CSC";
    private static final Logger logger = LoggerFactory.getLogger(TradeService.class);
    public static final String APPLICATION_CSI = "applicationCsi";
    public static final String APPLICATION_NAME = "applicationName";

    //generate jwt token
    public String generateToken(Token token) {
        // Define your secret key TTS Tech China - THE BEST CSC
        // Define the expiration date of the token
        Date expirationDate = new Date(System.currentTimeMillis() + 300000); // Token expires in 1 hour
        Claims claims = Jwts.claims();
        claims.put(APPLICATION_CSI, token.getApplicationCsi()); // Add applicationCsi claim
        claims.put(APPLICATION_NAME, token.getApplicationName()); // Add applicationName claim
        // Generate the token
        return Jwts.builder()
                .setClaims(claims)
                .setSubject("TTS Tech China - THE BEST CSC") // Set the subject of the token
                .setExpiration(expirationDate) // Set the expiration date of the token
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Sign the token with the secret key and algorithm
                .compact();
    }

    //validate jwt token
    public TokenValidateResult validateToken(String token, Token tokenbody){
        TokenValidateResult tokenValidateResult = new TokenValidateResult();
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token).getBody();
            String applicationCsi = claims.get(APPLICATION_CSI, String.class);
            String applicationName = claims.get(APPLICATION_NAME, String.class);

            Token token1 = new Token();
            token1.setApplicationCsi(applicationCsi);
            token1.setApplicationName(applicationName);
            tokenValidateResult.setToken(token1);
            if (applicationCsi.equals(tokenbody.getApplicationCsi())
                    && applicationName.equals(tokenbody.getApplicationName())){
                tokenValidateResult.setStatus("SUCCESS");
                logger.info("validate token success");
            }else{
                tokenValidateResult.setStatus("FAILED");
                logger.info("validate token failed");
            }
        } catch (Exception e) {
            tokenValidateResult.setStatus("FAILED");
            logger.info("validate token failed", e);
            // Handle the exception or return an error response
        }
        return tokenValidateResult;
    }
    public String convertJsonToXml(String json) throws Exception {
        return JsonXmlConverter.convertJsonToXml(json);
    }

    public String convertXmlToJson(String json) throws Exception {
        return JsonXmlConverter.convertXmlToJson(json);
    }


}
