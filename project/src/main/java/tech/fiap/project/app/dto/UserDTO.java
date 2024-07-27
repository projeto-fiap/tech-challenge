package tech.fiap.project.app.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

	private Long id;

	private String email;

	private String password;

	private List<DocumentDTO> document;

}