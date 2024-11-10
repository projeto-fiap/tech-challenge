package tech.fiap.project.app.service.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.domain.usecase.item.DeleteItemUseCase;

import static org.mockito.Mockito.verify;

class DeleteItemServiceTest {

	@Mock
	private DeleteItemUseCase deleteItemUseCase;

	@InjectMocks
	private DeleteItemService deleteItemService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void deleteItem_shouldCallDeleteItemUseCase() {
		Long itemId = 1L;

		deleteItemService.deleteItem(itemId);

		verify(deleteItemUseCase).execute(itemId);
	}

}