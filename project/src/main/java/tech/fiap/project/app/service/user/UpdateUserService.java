package tech.fiap.project.app.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.adapter.UserMapper;
import tech.fiap.project.app.dto.UserDTO;
import tech.fiap.project.domain.usecase.user.UpdateUserUseCase;

@AllArgsConstructor
@Service
public class UpdateUserService {

	private UpdateUserUseCase updateUserUseCase;

	public UserDTO update(UserDTO userDTO) {
		return UserMapper.toDTO(updateUserUseCase.update(userDTO.getEmail()));
	}

}
