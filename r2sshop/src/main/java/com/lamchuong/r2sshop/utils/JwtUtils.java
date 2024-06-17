package com.lamchuong.r2sshop.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

	private static final String SECRET = "5GuOhGTzsTk9EUa3vIrIlpglUvU9ZzKDwKGTi/yzm7LbpI54kGFKmWvf/l/xAhHS";

	public static String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public static Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public static Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public static String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, username);
	}

	private static String createToken(Map<String, Object> claims, String userName) {
		long currentTime = System.currentTimeMillis();
		long expiredTimeInMillis = 30 * 24 * 60 * 60 * 1_000L; // 30 days
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date(currentTime))
				.setExpiration(new Date(currentTime + expiredTimeInMillis))
				.signWith(getSignKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private static Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	private static Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	private static Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
}
