package tech.fiap.project.app.dto;

import org.junit.jupiter.api.Test;
import tech.fiap.project.infra.entity.ItemCategory;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemDTOTest {

	@Test
	void testGettersAndSetters() {
		// Cria uma instância do DTO
		ItemDTO dto = new ItemDTO();

		// Define valores para os campos
		dto.setId(1L);
		dto.setQuantity(new BigDecimal("10.5"));
		dto.setName("Pizza Margherita");
		dto.setUnit("un");
		dto.setPrice(new BigDecimal("45.90"));
		dto.setCategory(ItemCategory.FOOD);
		dto.setDescription("Delicious pizza with tomato, mozzarella, and basil");
		dto.setImageUrl("https://example.com/pizza.jpg");

		// Cria uma lista de ingredientes
		ItemDTO ingredient = new ItemDTO();
		ingredient.setId(2L);
		ingredient.setName("Tomato");
		ingredient.setQuantity(new BigDecimal("2.0"));
		dto.setIngredients(List.of(ingredient));

		// Verifica se os valores foram atribuídos corretamente
		assertThat(dto.getId()).isEqualTo(1L);
		assertThat(dto.getQuantity()).isEqualTo(new BigDecimal("10.5"));
		assertThat(dto.getName()).isEqualTo("Pizza Margherita");
		assertThat(dto.getUnit()).isEqualTo("un");
		assertThat(dto.getPrice()).isEqualTo(new BigDecimal("45.90"));
		assertThat(dto.getCategory()).isEqualTo(ItemCategory.FOOD);
		assertThat(dto.getDescription()).isEqualTo("Delicious pizza with tomato, mozzarella, and basil");
		assertThat(dto.getImageUrl()).isEqualTo("https://example.com/pizza.jpg");

		// Verifica a lista de ingredientes
		assertThat(dto.getIngredients()).hasSize(1);
		assertThat(dto.getIngredients().get(0).getId()).isEqualTo(2L);
		assertThat(dto.getIngredients().get(0).getName()).isEqualTo("Tomato");
		assertThat(dto.getIngredients().get(0).getQuantity()).isEqualTo(new BigDecimal("2.0"));
	}

	@Test
	void testEqualsAndHashCode() {
		// Cria duas instâncias com os mesmos valores
		ItemDTO dto1 = new ItemDTO();
		dto1.setId(1L);
		dto1.setName("Pizza Margherita");
		dto1.setPrice(new BigDecimal("45.90"));

		ItemDTO dto2 = new ItemDTO();
		dto2.setId(1L);
		dto2.setName("Pizza Margherita");
		dto2.setPrice(new BigDecimal("45.90"));

		// Verifica se são iguais
		assertThat(dto1).isEqualTo(dto2);
		assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());

		// Altera um valor e verifica se não são mais iguais
		dto2.setId(2L); // Altera o ID
		assertThat(dto1).isNotEqualTo(dto2);
	}

	@Test
	void testToString() {
		// Cria uma instância do DTO
		ItemDTO dto = new ItemDTO();
		dto.setId(1L);
		dto.setName("Pizza Margherita");
		dto.setPrice(new BigDecimal("45.90"));

		// Verifica se o método toString retorna uma representação válida
		String toStringResult = dto.toString();
		assertThat(toStringResult).contains("Pizza Margherita");
		assertThat(toStringResult).contains("45.90");
		assertThat(toStringResult).contains("id=1"); // Verifica se o ID está presente
	}

}