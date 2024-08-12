package tech.fiap.project.app.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tech.fiap.project.domain.entity.Role;

import java.util.List;

@Data
public class PersonDTO {

	private Long id;

	private String email;

	private String name;

	private String password;

	private Role role;

	private List<DocumentDTO> document;

}