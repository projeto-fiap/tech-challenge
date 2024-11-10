package tech.fiap.project.domain.usecase.impl.kitchen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.domain.dataprovider.KitchenDataProvider;
import tech.fiap.project.domain.entity.Kitchen;
import tech.fiap.project.domain.entity.KitchenStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class KitchenRetrieveUseCaseImplTest {

	@Mock
	private KitchenDataProvider kitchenDataProvider;

	@InjectMocks
	private KitchenRetrieveUseCaseImpl kitchenRetrieveUseCaseImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void findAll_returnsAllKitchens() {
		Kitchen kitchen1 = new Kitchen(1L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.AWAITING_PRODUCTION);
		Kitchen kitchen2 = new Kitchen(2L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.IN_PRODUCTION);
		List<Kitchen> kitchens = Arrays.asList(kitchen1, kitchen2);

		when(kitchenDataProvider.retrieveAll()).thenReturn(kitchens);

		List<Kitchen> result = kitchenRetrieveUseCaseImpl.findAll();

		assertEquals(2, result.size());
		assertEquals(kitchens, result);
	}

	@Test
	void findById_returnsKitchenWhenFound() {
		Kitchen kitchen = new Kitchen(1L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.AWAITING_PRODUCTION);

		when(kitchenDataProvider.retrieveById(1L)).thenReturn(Optional.of(kitchen));

		Optional<Kitchen> result = kitchenRetrieveUseCaseImpl.findById(1L);

		assertTrue(result.isPresent());
		assertEquals(kitchen, result.get());
	}

	@Test
	void findById_returnsEmptyWhenNotFound() {
		when(kitchenDataProvider.retrieveById(1L)).thenReturn(Optional.empty());

		Optional<Kitchen> result = kitchenRetrieveUseCaseImpl.findById(1L);

		assertTrue(result.isEmpty());
	}

	@Test
	void findIds_returnsKitchensByIds() {
		Kitchen kitchen1 = new Kitchen(1L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.AWAITING_PRODUCTION);
		Kitchen kitchen2 = new Kitchen(2L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.IN_PRODUCTION);
		List<Kitchen> kitchens = Arrays.asList(kitchen1, kitchen2);
		List<Long> ids = Arrays.asList(1L, 2L);

		when(kitchenDataProvider.retrieveAll()).thenReturn(kitchens);

		List<Kitchen> result = kitchenRetrieveUseCaseImpl.findIds(ids);

		assertEquals(2, result.size());
		assertEquals(kitchens, result);
	}

}