package tech.fiap.project.app.service.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.app.adapter.ItemMapper;
import tech.fiap.project.app.dto.CreateItemRequestDTO;
import tech.fiap.project.app.dto.ItemDTO;
import tech.fiap.project.domain.entity.Item;
import tech.fiap.project.domain.usecase.item.CreateItemUseCase;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

class CreateItemServiceTest {

	@Mock
	private CreateItemUseCase createItemUseCase;

	@InjectMocks
	private CreateItemService createItemService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void createItem_shouldReturnListOfItemDTOs() {
		List<CreateItemRequestDTO> requestDTOs = List.of(new CreateItemRequestDTO());
		List<Item> items = requestDTOs.stream().map(ItemMapper::toDomain).collect(Collectors.toList());
		List<ItemDTO> expectedItemDTOs = items.stream().map(ItemMapper::toDTO).collect(Collectors.toList());

		when(createItemUseCase.execute(anyList())).thenReturn(items);

		List<ItemDTO> result = createItemService.createItem(requestDTOs);

		assertEquals(expectedItemDTOs, result);
	}

}