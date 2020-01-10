package com.example.gateway.util.jwt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtHS256Util {

	private static final String HSA_SECRET = "hsa_secret";

	private static final long EXPIRE_TIME = 60 * 1000;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String token = createJWT();
		parseJWT(token);
	}

	public static String createJWT() {

		Date now = new Date();
		Date expireDate = new Date(now.getTime() + EXPIRE_TIME);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String expireStr = sdf.format(expireDate);
		Map<String,Object> claim = new HashMap<String, Object>();
		claim.put("name", "张三");
		claim.put("expire", expireStr);
		claim.put("signTime", sdf.format(now));
		String token = Jwts.builder()
				.setClaims(claim)
				.setSubject("")
				.setId(UUID.randomUUID().toString())
				.setIssuedAt(now)
				.signWith(SignatureAlgorithm.HS256, HSA_SECRET)
				.setExpiration(expireDate)
				.compact();

		System.out.println(token);
		return token;
	}

	public static Claims parseJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(HSA_SECRET).parseClaimsJws(token).getBody();
		System.out.println(claims);
		return claims;
	}

}