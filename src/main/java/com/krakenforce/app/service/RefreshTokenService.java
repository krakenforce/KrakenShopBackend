package com.krakenforce.app.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakenforce.app.exception.TokenRefreshException;
import com.krakenforce.app.model.RefreshToken;
import com.krakenforce.app.repository.RefreshTokenRepository;
import com.krakenforce.app.repository.UsersRepository;

@Service
@Transactional
public class RefreshTokenService {
	
	@Value("${kraken.app.jwtRefreshExpirationMs}")
	private Long refreshTokenDurationMs;
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	public Optional<RefreshToken> findByToken(String token){
		return refreshTokenRepository.findByToken(token);
	}
	
	public RefreshToken createRefreshToken(int userId) {
		RefreshToken refreshToken = new RefreshToken();
		
		refreshToken.setUser(usersRepository.findById(userId).get());
		refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
		refreshToken.setToken(UUID.randomUUID().toString());
		refreshToken = refreshTokenRepository.save(refreshToken);
		return refreshToken;
	}
	
	public RefreshToken verifyExpiration(RefreshToken token) {
		if(token.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(token);
			throw new TokenRefreshException(token.getToken(), "Refresh token was expired");
		}
		return token;
	}
	
	public int deleteByUserId(int userId) {
		return refreshTokenRepository.deleteByUser(usersRepository.findById(userId).get());
	}
}
