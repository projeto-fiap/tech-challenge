package tech.fiap.project.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDTO {

	private Long id;

	private String email;

	private String password;

	private List<DocumentDTO> document;

}