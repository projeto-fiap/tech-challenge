package tech.fiap.project.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

	private Long id;

	private String email;

	private String password;

}