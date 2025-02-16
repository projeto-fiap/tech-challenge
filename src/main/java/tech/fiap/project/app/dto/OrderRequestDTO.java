package tech.fiap.project.app.dto;

import lombok.Getter;
import lombok.Setter;
import tech.fiap.project.domain.entity.Person;

import java.util.List;

@Getter
@Setter
public class OrderRequestDTO {

	private Long id;

	private List<ItemRequestDTO> items;

	private Person person;

}