package tech.fiap.project.infra.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import tech.fiap.project.domain.entity.Role;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Email
	private String email;

	@NotEmpty
	private String password;

	@OneToMany()
	private List<OrderEntity> orders;

}