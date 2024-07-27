package tech.fiap.project.app.adapter;

import tech.fiap.project.app.dto.UserDTO;
import tech.fiap.project.domain.entity.User;
import tech.fiap.project.infra.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

	public static UserDTO toDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setEmail(user.getEmail());
		userDTO.setPassword(user.getPassword());
		userDTO.setDocument(DocumentMapper.toDTO(user.getDocument()));
		return userDTO;
	}

	public static User toDomain(UserDTO user) {
		return new User(user.getId(), user.getEmail(), user.getPassword(), DocumentMapper.toDomain(user.getDocument()));
	}

}
