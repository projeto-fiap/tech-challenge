package tech.fiap.project.domain.usecase.impl;

import tech.fiap.project.app.adapter.UserMapper;
import tech.fiap.project.domain.entity.User;
import tech.fiap.project.domain.usecase.UpdateUserUseCase;
import tech.fiap.project.domain.usecase.UserDataProvider;
import tech.fiap.project.infra.exception.UserNotFound;

public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    private final UserDataProvider userDataProvider;

    public UpdateUserUseCaseImpl(UserDataProvider userDataProvider) {
        this.userDataProvider = userDataProvider;
    }

    @Override
    public User update(String email) {
        return userDataProvider.retrieveByEmail(email)
                .map(user -> {
                    user.setEmail(email);
                    return userDataProvider.save(user);
                })
                .orElseThrow(() -> new UserNotFound(email));
    }
}
