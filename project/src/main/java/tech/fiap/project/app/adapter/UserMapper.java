package tech.fiap.project.app.adapter;

import tech.fiap.project.app.dto.UserDTO;
import tech.fiap.project.domain.entity.User;
import tech.fiap.project.infra.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
        public static UserDTO toDTO(User user) {
            return new UserDTO(user.getId(), user.getEmail(), user.getPassword());
       }

    public static User toDomain(UserDTO user) {
        return new User(user.getId(), user.getEmail(), user.getPassword());
    }

}
