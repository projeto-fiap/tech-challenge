package tech.fiap.project.infra.configuration.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.fiap.project.infra.entity.PersonEntity;
import tech.fiap.project.infra.exception.PersonNotFoundException;
import tech.fiap.project.infra.repository.PersonRepository;
import tech.fiap.project.domain.entity.DocumentType;

import java.util.Optional;

@Service
public class CustomPersonDetailsService implements UserDetailsService {

	private final PersonRepository personRepository;

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public CustomPersonDetailsService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
		this.personRepository = personRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		Optional<PersonEntity> person = personRepository.findByDocuments_TypeAndDocuments_Value(DocumentType.CPF, cpf);
		if (person.isEmpty()) {
			throw new PersonNotFoundException(cpf);
		}
		PersonEntity personEntity = person.get();
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + personEntity.getRole());
		return org.springframework.security.core.userdetails.User.withUsername(cpf)
				.password(passwordEncoder.encode(personEntity.getPassword())).authorities(authority).build();
	}

}
