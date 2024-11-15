package tech.fiap.project.app.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemRequestDTO {

	private Long id;

	private BigDecimal quantity;

	private String unit;

}
