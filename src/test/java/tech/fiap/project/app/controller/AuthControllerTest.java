package tech.fiap.project.app.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tech.fiap.project.infra.configuration.authorization.CustomPersonDetailsService;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

	private MockMvc mockMvc;

	@Mock
	private CustomPersonDetailsService customPersonDetailsService;

	private AuthController authController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this); // Inicializa os mocks manualmente
		authController = new AuthController(customPersonDetailsService);
		mockMvc = MockMvcBuilders.standaloneSetup(authController).build(); // Configura o
																			// MockMvc sem
																			// Spring
																			// Context
	}

	@Test
	public void testLoginSuccess() throws Exception {
		// Simula uma resposta bem-sucedida para o login
		String cpf = "12345678901";
		String password = "developer";
		String token = "generated_token";

		when(customPersonDetailsService.authenticateAndGenerateToken(cpf, password)).thenReturn(token);

		mockMvc.perform(MockMvcRequestBuilders.post("/auth/login").param("cpf", cpf).param("password", password)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()) // Espera
																											// status
																											// 200
																											// OK
				.andExpect(MockMvcResultMatchers.content().string(token)); // Espera que o
																			// conteúdo da
																			// resposta
																			// seja o
																			// token
																			// gerado
	}

	@Test
	public void testLoginInvalidCredentials() throws Exception {
		String cpf = "12345678901";
		String password = "wrongpassword";

		when(customPersonDetailsService.authenticateAndGenerateToken(cpf, password))
				.thenThrow(new UsernameNotFoundException("Invalid credentials"));

		mockMvc.perform(MockMvcRequestBuilders.post("/auth/login").param("cpf", cpf).param("password", password)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isUnauthorized()) // Espera
																														// status
																														// 401
																														// Unauthorized
				.andExpect(MockMvcResultMatchers.content().string("Invalid credentials")); // Espera
																							// que
																							// o
																							// conteúdo
																							// seja
																							// "Invalid
																							// credentials"
	}

	@Test
	public void testLoginBadRequest() throws Exception {
		String cpf = "12345678901";
		String password = "developer";

		when(customPersonDetailsService.authenticateAndGenerateToken(cpf, password))
				.thenThrow(new RuntimeException("Bad request"));

		mockMvc.perform(MockMvcRequestBuilders.post("/auth/login").param("cpf", cpf).param("password", password)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest()) // Espera
																													// status
																													// 400
																													// Bad
																													// Request
				.andExpect(MockMvcResultMatchers.content().string("Bad request: Bad request")); // Espera
																								// que
																								// o
																								// conteúdo
																								// seja
																								// "Bad
																								// request"
	}

}
