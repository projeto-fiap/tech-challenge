package tech.fiap.project.infra.configuration.authorization;

import tech.fiap.project.infra.entity.PersonEntity;
import tech.fiap.project.infra.exception.PersonNotFoundException;
import tech.fiap.project.infra.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
	public UserDetails loadUserByUsername(String personname) throws UsernameNotFoundException {
		Optional<PersonEntity> person = personRepository.findByEmail(personname);
		if (person.isEmpty()) {
			throw new PersonNotFoundException(personname);
		}
		PersonEntity personEntity = person.get();
		return org.springframework.security.core.userdetails.User.withUsername(personEntity.getEmail())
				.password(passwordEncoder.encode(personEntity.getPassword())).roles("USER").build();
	}

}