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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class KitchenUpdateUseCaseImplTest {

	@Mock
	private KitchenDataProvider kitchenDataProvider;

	@InjectMocks
	private KitchenUpdateUseCaseImpl kitchenUpdateUseCaseImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void execute_updatesKitchenSuccessfully() {
		Kitchen kitchen = new Kitchen(1L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.IN_PRODUCTION);

		when(kitchenDataProvider.create(kitchen)).thenReturn(kitchen);

		Kitchen result = kitchenUpdateUseCaseImpl.execute(kitchen);

		assertEquals(kitchen.getOrderId(), result.getOrderId());
		assertEquals(kitchen.getCreationDate(), result.getCreationDate());
		assertEquals(kitchen.getUpdatedDate(), result.getUpdatedDate());
		assertEquals(kitchen.getStatus(), result.getStatus());
	}

}