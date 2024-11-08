package tech.fiap.project.app.service.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.app.adapter.ItemMapper;
import tech.fiap.project.app.dto.ItemDTO;
import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.domain.usecase.item.RetrieveItemUseCase;
import tech.fiap.project.infra.entity.ItemCategory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class RetrieveItemServiceTest {

	@Mock
	private RetrieveItemUseCase retrieveItemUseCase;

	@InjectMocks
	private RetrieveItemService retrieveItemService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void findAll_shouldReturnListOfItemDTOs() {
		Item item = new Item(1L, "Item 1", BigDecimal.valueOf(10.00), BigDecimal.valueOf(2), "unit", ItemCategory.FOOD,
				null, "description", "imageUrl");
		List<Item> items = List.of(item);
		List<ItemDTO> expectedItemDTOs = items.stream().map(ItemMapper::toDTO).toList();

		when(retrieveItemUseCase.findAll()).thenReturn(items);

		List<ItemDTO> result = retrieveItemService.findAll();

		assertEquals(expectedItemDTOs, result);
	}

	@Test
	void findById_shouldReturnItemDTO() {
		Long itemId = 1L;
		Item item = new Item(itemId, "Item 1", BigDecimal.valueOf(10.00), BigDecimal.valueOf(2), "unit",
				ItemCategory.FOOD, null, "description", "imageUrl");
		ItemDTO expectedItemDTO = ItemMapper.toDTO(item);

		when(retrieveItemUseCase.findById(itemId)).thenReturn(Optional.of(item));

		Optional<ItemDTO> result = retrieveItemService.findById(itemId);

		assertTrue(result.isPresent());
		assertEquals(expectedItemDTO, result.get());
	}

	@Test
	void findByCategory_shouldReturnListOfItemDTOs() {
		String category = "FOOD";
		ItemCategory itemCategory = ItemCategory.valueOf(category.toUpperCase());
		Item item = new Item(1L, "Item 1", BigDecimal.valueOf(10.00), BigDecimal.valueOf(2), "unit", itemCategory, null,
				"description", "imageUrl");
		List<Item> items = List.of(item);
		List<ItemDTO> expectedItemDTOs = items.stream().map(ItemMapper::toDTO).toList();

		when(retrieveItemUseCase.findByCategory(itemCategory)).thenReturn(items);

		List<ItemDTO> result = retrieveItemService.findByCategory(category);

		assertEquals(expectedItemDTOs, result);
	}

}