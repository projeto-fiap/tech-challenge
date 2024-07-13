package tech.fiap.project.app.adapter;

import org.springframework.stereotype.Service;
import tech.fiap.project.app.dto.OrderDTO;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.entity.User;
import tech.fiap.project.infra.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {
       public UserEntity toEntity(User user) {
           UserEntity userEntity = new UserEntity();
          userEntity.setId(user.getId());
            userEntity.setEmail(user.getEmail());
            userEntity.setPassword(user.getPassword());
           return userEntity;
       }

        public User toDTO(UserEntity user) {
            return new User(user.getId(), user.getEmail(), user.getPassword());
       }

    public List<User> toDTO(List<UserEntity> user) {
        return user.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<UserEntity> toEntity(List<User> user) {
        return user.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
