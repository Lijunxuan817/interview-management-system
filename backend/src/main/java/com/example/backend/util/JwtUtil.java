package com.example.backend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import com.example.backend.common.UnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtUtil {

	private final SecretKey key;
	private final long expireHours;

	public JwtUtil(
			@Value("${app.jwt.secret}") String secret,
			@Value("${app.jwt.expire-hours}") long expireHours) {
		this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
		this.expireHours = expireHours;
	}

	public String createToken(Long userId, String email, String role) {
		Instant now = Instant.now();
		return Jwts.builder()
				.subject(String.valueOf(userId))
				.claim("email", email)
				.claim("role", role)
				.issuedAt(Date.from(now))
				.expiration(Date.from(now.plus(expireHours, ChronoUnit.HOURS)))
				.signWith(key)
				.compact();
	}

	public Long parseUserId(String token) {
		return Long.parseLong(parseClaims(token).getSubject());
	}

	public String parseRole(String token) {
		return parseClaims(token).get("role", String.class);
	}

	private Claims parseClaims(String token) {
		try {
			return Jwts.parser()
					.verifyWith(key)
					.build()
					.parseSignedClaims(token)
					.getPayload();
		} catch (Exception e) {
			throw new UnauthorizedException("登录已失效，请重新登录");
		}
	}
}
