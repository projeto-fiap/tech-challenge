package tech.fiap.project.infra.dataprovider;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.domain.entity.DocumentType;
import tech.fiap.project.domain.entity.User;
import tech.fiap.project.domain.dataprovider.UserDataProvider;
import tech.fiap.project.infra.mapper.UserRepositoryMapper;
import tech.fiap.project.infra.entity.QDocumentEntity;
import tech.fiap.project.infra.entity.QUserEntity;
import tech.fiap.project.infra.entity.UserEntity;
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
	public Optional<User> retrieveByCPF(String cpf) {
		QUserEntity userEntity = QUserEntity.userEntity;
		QDocumentEntity anyDocument = userEntity.documents.any();
		BooleanExpression predicate = anyDocument.type.eq(DocumentType.CPF).and(anyDocument.value.eq(cpf));
		return UserRepositoryMapper.toDomain(userRepository.findOne(predicate));
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
