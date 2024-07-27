package tech.fiap.project.infra.mapper;

import tech.fiap.project.domain.entity.User;
import tech.fiap.project.infra.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepositoryMapper {

	public static List<User> toDomain(List<UserEntity> users) {
		return users.stream().map(UserRepositoryMapper::toDomain).collect(Collectors.toList());
	}

	public static User toDomain(UserEntity user) {
		return new User(user.getId(), user.getEmail(), user.getPassword(),
				DocumentRepositoryMapper.toDomain(user.getDocuments()));
	}

	public static Optional<User> toDomain(Optional<UserEntity> user) {
		return user.map(UserRepositoryMapper::toDomain);
	}

	public static List<UserEntity> toEntity(List<User> users) {
		return users.stream().map(UserRepositoryMapper::toEntity).collect(Collectors.toList());
	}

	public static UserEntity toEntity(User user) {
		if (user == null) {
			return null;
		}
		UserEntity userEntity = new UserEntity();
		userEntity.setId(user.getId());
		userEntity.setEmail(user.getEmail());
		userEntity.setPassword(user.getPassword());
		userEntity.setDocuments(DocumentRepositoryMapper.toEntity(user.getDocument()));
		return userEntity;
	}

}
