package tech.fiap.project.infra.configuration.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.fiap.project.infra.configuration.authorization.util.JwtUtil;
import tech.fiap.project.infra.entity.PersonEntity;
import tech.fiap.project.infra.exception.PersonNotFoundException;
import tech.fiap.project.infra.repository.PersonRepository;
import tech.fiap.project.domain.entity.DocumentType;

import java.util.Optional;
@Service
public class CustomPersonDetailsService implements UserDetailsService {

	private final PersonRepository personRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	@Autowired
	public CustomPersonDetailsService(PersonRepository personRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
		this.personRepository = personRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		PersonEntity personEntity = personRepository
				.findByDocuments_TypeAndDocuments_Value(DocumentType.CPF, cpf)
				.orElseThrow(() -> new PersonNotFoundException(cpf));

		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + personEntity.getRole());
		return org.springframework.security.core.userdetails.User
				.withUsername(cpf)
				.password(personEntity.getPassword()) // A senha já deve estar criptografada no banco
				.authorities(authority)
				.build();
	}

	public String authenticateAndGenerateToken(String cpf, String password) {
		PersonEntity personEntity = personRepository
				.findByDocuments_TypeAndDocuments_Value(DocumentType.CPF, cpf)
				.orElseThrow(() -> new PersonNotFoundException(cpf));
		if (!passwordEncoder.matches(password, personEntity.getPassword())) {
			throw new UsernameNotFoundException("Invalid credentials");
		}

		return jwtUtil.generateToken(cpf);
	}
}

