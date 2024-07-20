package tech.fiap.project.infra.dataprovider;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.User;
import tech.fiap.project.domain.usecase.UserDataProvider;
import tech.fiap.project.infra.dataprovider.mapper.OrderRepositoryMapper;
import tech.fiap.project.infra.dataprovider.mapper.UserRepositoryMapper;
import tech.fiap.project.infra.entity.OrderEntity;
import tech.fiap.project.infra.entity.UserEntity;
import tech.fiap.project.infra.repository.OrderRepository;
import tech.fiap.project.infra.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDataProviderImpl implements UserDataProvider {

	private UserRepository userRepository;

	@Override
	public List<User> retrieveAll() {
		return UserRepositoryMapper.toDomain(userRepository.findAll());
	}

	@Override
	public Optional<User> retrieveByEmail(String email) {
		Optional<UserEntity> byEmail = userRepository.findByEmail(email);
		return byEmail.map(UserRepositoryMapper::toDomain);
	}

	@Override
	public Optional<User> retrieveById(Long id) {
		Optional<UserEntity> byId = userRepository.findById(id);
		return byId.map(UserRepositoryMapper::toDomain);
	}

	@Override
	public User save(User user) {
		return UserRepositoryMapper.toDomain(userRepository.save(UserRepositoryMapper.toEntity(user)));
	}

	@Override
	public void delete(User user) {
		userRepository.delete(UserRepositoryMapper.toEntity(user));
	}

}
