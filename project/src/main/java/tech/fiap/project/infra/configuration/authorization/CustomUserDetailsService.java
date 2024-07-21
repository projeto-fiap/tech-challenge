package tech.fiap.project.infra.configuration.authorization;

import tech.fiap.project.infra.entity.UserEntity;
import tech.fiap.project.infra.exception.UserNotFoundException;
import tech.fiap.project.infra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> user = userRepository.findByEmail(username);
		if (user.isEmpty()) {
			throw new UserNotFoundException(username);
		}
		UserEntity userEntity = user.get();
		return org.springframework.security.core.userdetails.User.withUsername(userEntity.getEmail())
				.password(passwordEncoder.encode(userEntity.getPassword())).roles("USER").build();
	}

}