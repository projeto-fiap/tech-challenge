package tech.fiap.project.domain.usecase.impl.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.domain.dataprovider.ItemDataProvider;

import static org.mockito.Mockito.verify;

class DeleteItemUseCaseImplTest {

	@Mock
	private ItemDataProvider itemDataProvider;

	@InjectMocks
	private DeleteItemUseCaseImpl deleteItemUseCaseImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void execute_deletesItemById() {
		Long itemId = 1L;

		deleteItemUseCaseImpl.execute(itemId);

		verify(itemDataProvider).deleteById(itemId);
	}

}
