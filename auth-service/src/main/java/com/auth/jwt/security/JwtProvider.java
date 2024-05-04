package com.auth.jwt.security;

import com.auth.jwt.dto.RequestDto;
import com.auth.jwt.model.AuthUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class JwtProvider {
    private Key secret;

    @Autowired
    private RouteValidator routeValidator;

    @PostConstruct
    protected void init(){
        byte[] apiKeySecretBytes = new byte[64];//llave de 512 bits
        new SecureRandom().nextBytes(apiKeySecretBytes);
        secret = Keys.hmacShaKeyFor(apiKeySecretBytes);
    }

    public String createToken(AuthUser user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",user.getId());
        claims.put("role",user.getRole());
        claims.put("company","AulaMatriz");
        LocalDateTime now = LocalDateTime.now();
        return Jwts
                .builder()
                .claims(claims)
                .subject(user.getUserName())
                .issuedAt(this.convertLocalDateTimeToDate(now))
                .expiration(this.convertLocalDateTimeToDate(now.plusHours(12)))
                .signWith(Keys.hmacShaKeyFor(secret.getEncoded()))
                .compact();
    }

    private  boolean isAdmin(String token){
        return Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getEncoded()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role")
                .equals("admin");
    }

    public boolean validate(String token, RequestDto requestDto){
        try {
            Jwts
                    .parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getEncoded()))
                    .build()
                    .parseSignedClaims(token);
        }catch (Exception e){
            return false;
        }
        if(!isAdmin(token) && routeValidator.isAdmin(requestDto) ){
                return false;
        }
        return true;
    }

    public String getUserNameFromToken(String token){
        try {
            return Jwts
                    .parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getEncoded()))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        }catch (Exception e){
            return "bad token";
        }
    }


    private Date convertLocalDateTimeToDate(LocalDateTime localDateTime){
        return Date
                .from(
                        localDateTime.atZone(
                                ZoneId.systemDefault()
                        ).toInstant()
                );
    }
}
