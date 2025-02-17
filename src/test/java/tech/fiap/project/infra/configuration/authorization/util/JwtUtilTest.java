package tech.fiap.project.infra.configuration.authorization.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.Key;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

	private JwtUtil jwtUtil;

	private Key key;

	@BeforeEach
	void setUp() {
		key = Keys.hmacShaKeyFor("mySecretKeymySecretKeymySecretKeymySecretKey".getBytes());
		jwtUtil = new JwtUtil(key.toString());
	}

	@Test
	void testGenerateToken() {
		String username = "testUser";
		String token = jwtUtil.generateToken(username);

		assertNotNull(token);
		assertTrue(token.length() > 0);
	}

	@Test
	void testExtractUsername() {
		String username = "testUser";
		String token = jwtUtil.generateToken(username);

		String extractedUsername = jwtUtil.extractUsername(token);

		assertEquals(username, extractedUsername);
	}

	@Test
	void testValidateToken() {
		String username = "testUser";
		String token = jwtUtil.generateToken(username);

		boolean isValid = jwtUtil.validateToken(token);

		assertTrue(isValid);
	}

	@Test
	void testValidateToken_InvalidToken() {
		String invalidToken = "invalidToken";

		boolean isValid = jwtUtil.validateToken(invalidToken);

		assertFalse(isValid);
	}

	@Test
	void testValidateToken_ExpiredToken() {
		String username = "testUser";
		String expiredToken = Jwts.builder().setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis() - 10000))
				.setExpiration(new Date(System.currentTimeMillis() - 5000)).signWith(key, SignatureAlgorithm.HS256)
				.compact();

		boolean isValid = jwtUtil.validateToken(expiredToken);

		assertFalse(isValid);
	}

	@Test
	void testValidateToken_NullToken() {
		boolean isValid = jwtUtil.validateToken(null);

		assertFalse(isValid);
	}

}