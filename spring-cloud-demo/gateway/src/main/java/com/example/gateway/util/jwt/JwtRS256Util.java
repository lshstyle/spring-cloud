package com.example.gateway.util.jwt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JwtRS256Util {

    public static final long EXPIRE_TIME = 60*1000;
    public static String createJWT() throws Exception {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + EXPIRE_TIME);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String expireStr = sdf.format(expireDate);
        Map<String,Object> claim = new HashMap<String, Object>();
        claim.put("name", "张三");
        claim.put("expire", expireStr);
        claim.put("signTime", sdf.format(now));
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.RS256, SecretUtil.getPrivateKey())
                .setIssuedAt(now)
                .setClaims(claim)
                .setId(UUID.randomUUID().toString())
                .setExpiration(expireDate)
                .compact();
        return token;
    }

    public static Claims parseJWT(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, Exception {
        Claims claims = Jwts.parser().setSigningKey(SecretUtil.getPublicKey()).parseClaimsJws(token).getBody();
        System.out.println(claims);
        return claims;
    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        String token = createJWT();
        parseJWT(token);
    }

}