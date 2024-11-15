package tech.fiap.project.app.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class OrderRequestDTO {

	private Long id;

	private List<ItemRequestDTO> items;

	private PersonDTO person;

}