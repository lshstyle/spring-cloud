package com.example.gateway.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

public static final String HSA_SECRET = "hsa_secret";
	
	public static final String RSA_SECRET_PUBLIC = "rsa_secret_public";
	
	public static final String RSA_SECRET_PRIVATE = "rsa_secret_private";
	
	public static final long expireTime = 60*1000;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String token = createJWT();
		
		parseJWT(token);
	}
	
	public static String createJWT() {
		return createJWTByHS256();
	}
	
	//eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi5byg5LiJIiwiZXhwIjoxNTc4NTU1MDIzLCJpYXQiOjE1Nzg1NTQ5NjMsImp0aSI6ImMxYzk5YTczLWQ1YjYtNDdmNC1hODE1LThlYTMwZjJiNGYyNCJ9.oaAiEt3dUm44tYI3ORdAm4yXlGaEp8fWRb1v057-R8c
	public static String createJWTByHS256() {
		SignatureAlgorithm signaturealgorithm = SignatureAlgorithm.HS256;
		Map<String, Object> claims  = new HashMap<String, Object>();
		claims.put("name", "张三");
		Date now  = new Date();
		JwtBuilder builder = Jwts.builder()
				.setClaims(claims)
				.setId(UUID.randomUUID().toString())
				.setIssuedAt(now)
				.signWith(signaturealgorithm, HSA_SECRET);
		builder.setExpiration(new Date(now.getTime() + expireTime));
		String token = builder.compact();
		System.out.println(token);
		return token;
	}
	
	public static Claims parseJWT(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(HSA_SECRET)
				.parseClaimsJws(token).getBody();
		System.out.println(claims);
		return claims;
	}
	

}
