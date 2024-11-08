package tech.fiap.project.app.service.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.app.adapter.ItemMapper;
import tech.fiap.project.app.dto.ItemDTO;
import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.domain.usecase.item.UpdateItemUseCase;
import tech.fiap.project.infra.entity.ItemCategory;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class UpdateItemServiceTest {

	@Mock
	private UpdateItemUseCase updateItemUseCase;

	@InjectMocks
	private UpdateItemService updateItemService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void updateItem_shouldReturnUpdatedItemDTO() {
		Long itemId = 1L;
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setId(1L);
		itemDTO.setName("Item 1");
		itemDTO.setPrice(BigDecimal.valueOf(10.00));
		itemDTO.setQuantity(BigDecimal.valueOf(2));
		itemDTO.setUnit("unit");
		itemDTO.setCategory(ItemCategory.FOOD);
		itemDTO.setDescription("description");
		itemDTO.setImageUrl("imageUrl");

		Item updatedItem = new Item(1L, "Updated Item", BigDecimal.valueOf(15.00), BigDecimal.valueOf(3), "unit",
				ItemCategory.FOOD, null, "updated description", "updated imageUrl");

		ItemDTO expectedItemDTO = ItemMapper.toDTO(updatedItem);

		when(updateItemUseCase.execute(eq(itemId), any(Item.class))).thenReturn(updatedItem);

		ItemDTO result = updateItemService.updateItem(itemId, itemDTO);

		assertEquals(expectedItemDTO, result);
	}

}