package tech.fiap.project.infra.configuration.authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.fiap.project.infra.configuration.authorization.util.JwtUtil;
import tech.fiap.project.infra.entity.PersonEntity;
import tech.fiap.project.infra.exception.PersonNotFoundException;
import tech.fiap.project.infra.repository.PersonRepository;
import tech.fiap.project.domain.entity.DocumentType;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomPersonDetailsService implements UserDetailsService {

	private final PersonRepository personRepository;

	private final PasswordEncoder passwordEncoder;

	private final JwtUtil jwtUtil;

	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		PersonEntity personEntity = personRepository.findByDocuments_TypeAndDocuments_Value(DocumentType.CPF, cpf)
				.orElseThrow(() -> new PersonNotFoundException(cpf));

		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + personEntity.getRole());
		return org.springframework.security.core.userdetails.User.withUsername(cpf).password(personEntity.getPassword())
				.authorities(authority).build();
	}

	public String authenticateAndGenerateToken(String cpf, String password) {
		Optional<PersonEntity> personEntityOpt = personRepository
				.findByDocuments_TypeAndDocuments_Value(DocumentType.CPF, cpf);

		String dummyHash = "$2a$10$DUMMY_HASH_INVALID_CREDENTIAL";

		String hashedPassword = personEntityOpt.map(PersonEntity::getPassword).orElse(dummyHash);

		if (!passwordEncoder.matches(password, hashedPassword)) {
			throw new UsernameNotFoundException("Invalid credentials");
		}

		return jwtUtil.generateToken(cpf);
	}

}
