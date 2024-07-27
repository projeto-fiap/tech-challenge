package tech.fiap.project.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class DiscountDTO {

	private BigDecimal amount;

	private String description;

	private String code;

	private DetailDTO detail;

}
