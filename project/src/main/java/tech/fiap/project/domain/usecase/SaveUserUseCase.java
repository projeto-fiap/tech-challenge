package tech.fiap.project.domain.usecase;

import tech.fiap.project.domain.entity.User;

public interface SaveUserUseCase {
    User save(User user);
}
