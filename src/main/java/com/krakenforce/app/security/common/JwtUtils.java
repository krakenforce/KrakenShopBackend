package com.krakenforce.app.security.common;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.krakenforce.app.security.services.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {

	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${kraken.app.jwtSecret}")
	private String jwtSecret;
	
	@Value("${kraken.app.jwtExpirationMs}")
	private String jwtExpirationMs;
	
	
	public String generateJwtToken(Authentication authentication) {
		Date date1 = new Date();
        long t1 = date1.getTime();
				
		UserDetailsImpl userPrinciple = (UserDetailsImpl) authentication.getPrincipal();
		
		return Jwts.builder()
				.setSubject(userPrinciple.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(t1 + 60000))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	
	public  String getUsernameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();	
	}
	
	
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		}catch( SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		}catch( MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		}catch( ExpiredJwtException e) {
			logger.error("JWT Token is expired: {}", e.getMessage());
		}catch( UnsupportedJwtException e) {
			logger.error("JWT Token is unsupported: {}", e.getMessage());
		}catch( IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}
		return false;
	}
}
