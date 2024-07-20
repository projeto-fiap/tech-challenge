package tech.fiap.project.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.dto.UserDTO;
import tech.fiap.project.domain.usecase.DeleteUserUseCase;

@AllArgsConstructor
@Service
public class DeleteUserService {

        private DeleteUserUseCase deleteUserService;
        public void delete(UserDTO userDTO) {
            deleteUserService.delete(userDTO.getEmail());
        }
}
