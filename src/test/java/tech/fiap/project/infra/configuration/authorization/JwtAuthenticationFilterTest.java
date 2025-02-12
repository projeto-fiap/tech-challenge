package tech.fiap.project.infra.configuration.authorization;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import tech.fiap.project.infra.configuration.authorization.util.JwtUtil;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationFilterTest {

	@Mock
	private JwtUtil jwtUtil;

	@Mock
	private CustomPersonDetailsService customPersonDetailsService;

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private FilterChain filterChain;

	@InjectMocks
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	private final String token = "dummyToken";

	private final String username = "testUser";

	@BeforeEach
	void setUp() {
		SecurityContextHolder.clearContext();
	}


	@Test
	void testDoFilterInternal_NoAuthorizationHeader() throws ServletException, IOException {
		when(request.getHeader("Authorization")).thenReturn(null);

		jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

		verify(filterChain).doFilter(request, response);

		assertNull(SecurityContextHolder.getContext().getAuthentication());
	}

	@Test
	void testDoFilterInternal_InvalidAuthorizationHeader() throws ServletException, IOException {
		when(request.getHeader("Authorization")).thenReturn("InvalidHeader");

		jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

		verify(filterChain).doFilter(request, response);

		assertNull(SecurityContextHolder.getContext().getAuthentication());
	}

}