package tech.fiap.project.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.domain.entity.User;
import tech.fiap.project.domain.usecase.SaveUserUseCase;

@AllArgsConstructor
@Service
public class SaveUserService {

    private SaveUserUseCase saveUserUseCase;
    public User save(User user) {
        return saveUserUseCase.save(user);
    }
}
