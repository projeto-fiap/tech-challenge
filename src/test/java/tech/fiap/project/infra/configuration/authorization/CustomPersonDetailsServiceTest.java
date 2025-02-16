package tech.fiap.project.infra.configuration.authorization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import tech.fiap.project.domain.entity.Role;
import tech.fiap.project.infra.configuration.authorization.util.JwtUtil;
import tech.fiap.project.infra.entity.PersonEntity;
import tech.fiap.project.infra.exception.PersonNotFoundException;
import tech.fiap.project.infra.repository.PersonRepository;
import tech.fiap.project.domain.entity.DocumentType;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomPersonDetailsServiceTest {

	@Mock
	private PersonRepository personRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Mock
	private JwtUtil jwtUtil;

	@InjectMocks
	private CustomPersonDetailsService customPersonDetailsService;

	private final String cpf = "12345678901";

	private final String password = "password";

	private final String hashedPassword = "$2a$10$DUMMY_HASH";

	private final String token = "dummyToken";

	@Test
	void testLoadUserByUsername_Success() {
		PersonEntity personEntity = new PersonEntity();
		personEntity.setPassword(hashedPassword);
		personEntity.setRole(Role.USER);

		when(personRepository.findByDocuments_TypeAndDocuments_Value(DocumentType.CPF, cpf))
				.thenReturn(Optional.of(personEntity));

		UserDetails userDetails = customPersonDetailsService.loadUserByUsername(cpf);

		assertNotNull(userDetails);
		assertEquals(cpf, userDetails.getUsername());
		assertEquals(hashedPassword, userDetails.getPassword());
		assertTrue(userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));
	}

	@Test
	void testAuthenticateAndGenerateToken_Success() {
		PersonEntity personEntity = new PersonEntity();
		personEntity.setPassword(hashedPassword);

		when(personRepository.findByDocuments_TypeAndDocuments_Value(DocumentType.CPF, cpf))
				.thenReturn(Optional.of(personEntity));
		when(passwordEncoder.matches(password, hashedPassword)).thenReturn(true);
		when(jwtUtil.generateToken(cpf)).thenReturn(token);

		String generatedToken = customPersonDetailsService.authenticateAndGenerateToken(cpf, password);

		assertEquals(token, generatedToken);
	}

	@Test
	void testAuthenticateAndGenerateToken_InvalidCredentials() {
		String dummyHash = "$2a$10$DUMMY_HASH_INVALID_CREDENTIAL";

		when(personRepository.findByDocuments_TypeAndDocuments_Value(DocumentType.CPF, cpf))
				.thenReturn(Optional.empty());
		when(passwordEncoder.matches(password, dummyHash)).thenReturn(false);

		assertThrows(UsernameNotFoundException.class, () -> {
			customPersonDetailsService.authenticateAndGenerateToken(cpf, password);
		});
	}

	@Test
	void testAuthenticateAndGenerateToken_PasswordMismatch() {
		PersonEntity personEntity = new PersonEntity();
		personEntity.setPassword(hashedPassword);

		when(personRepository.findByDocuments_TypeAndDocuments_Value(DocumentType.CPF, cpf))
				.thenReturn(Optional.of(personEntity));
		when(passwordEncoder.matches(password, hashedPassword)).thenReturn(false);

		assertThrows(UsernameNotFoundException.class, () -> {
			customPersonDetailsService.authenticateAndGenerateToken(cpf, password);
		});
	}

}