package tech.fiap.project.infra.dataprovider.mapper;

import tech.fiap.project.domain.entity.User;
import tech.fiap.project.infra.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryMapper {

    public static List<User> toDomain(List<UserEntity> users) {
        return users.stream().map(UserRepositoryMapper::toDomain).collect(Collectors.toList());

    }

    public static User toDomain(UserEntity user) {
        return new User(user.getId(), user.getEmail(), user.getPassword());
    }

    public static List<UserEntity> toEntity(List<User> users) {
        return users.stream().map(UserRepositoryMapper::toEntity).collect(Collectors.toList());

    }

    public static UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        return userEntity;
    }

}
