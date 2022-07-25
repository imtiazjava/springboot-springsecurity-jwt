package com.cgi.main.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtUtil {

	@Value("${app.secret}")
	private String secret;
	
	//6. Validate the  Token
	public  boolean validateToken(String token,String username) {
		String tokenUsername=getUserName(token);
		return username.equals(tokenUsername) && !isTokenExp(token);
	}
	//5.Validate Exp Date
	public boolean isTokenExp(String tok) {
		return getExpDate(tok).before(new Date(System.currentTimeMillis()));
	}
	
	//4. Read Subject/username
	public String getUserName(String toke) {
		return getClaims(toke).getSubject();
	}
	
	//3. Read Exp Date
	public Date getExpDate(String token) {
		return getClaims(token).getExpiration();
	}
	//2.Read Claims
	@SuppressWarnings("deprecation")
	public Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(secret.getBytes())
		    .parseClaimsJws(token)
		    .getBody();
	}
	
	//1.Generate Token
	@SuppressWarnings("deprecation")
	public String generateToken(String subject) {
		return Jwts.builder()
			.setSubject("CGI")
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(15)))
			.signWith(SignatureAlgorithm.HS512,secret.getBytes())
			.compact();
		
	}
	
}
