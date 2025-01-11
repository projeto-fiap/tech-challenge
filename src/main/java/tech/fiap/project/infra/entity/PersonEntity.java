package tech.fiap.project.infra.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.ToString;
import tech.fiap.project.domain.entity.Role;

import java.util.List;

@Entity
@Table(name = "\"person\"")
@Data
public class PersonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String name;

	@Email
	private String email;

	@NotEmpty
	private String password;

	@OneToMany
	private List<OrderEntity> orders;

	@NotEmpty
	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@ToString.Exclude
	private List<DocumentEntity> documents;
}
