package tech.fiap.project.infra.dataprovider;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.domain.entity.Kitchen;
import tech.fiap.project.domain.entity.KitchenStatus;
import tech.fiap.project.infra.entity.KitchenEntity;
import tech.fiap.project.infra.repository.KitchenRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class KitchenDataProviderImplTest {

	@Mock
	private KitchenRepository kitchenRepository;

	@InjectMocks
	private KitchenDataProviderImpl kitchenDataProvider;

	public KitchenDataProviderImplTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void retrieveAll_shouldReturnListOfKitchens() {
		List<KitchenEntity> kitchenEntities = List.of(new KitchenEntity(), new KitchenEntity());
		when(kitchenRepository.findAll()).thenReturn(kitchenEntities);

		List<Kitchen> kitchens = kitchenDataProvider.retrieveAll();

		assertEquals(kitchenEntities.size(), kitchens.size());
		verify(kitchenRepository, times(1)).findAll();
	}

	@Test
	void retrieveAllIds_shouldReturnListOfKitchens() {
		List<KitchenEntity> kitchenEntities = List.of(new KitchenEntity(), new KitchenEntity());
		List<Long> ids = List.of(1L, 2L);
		when(kitchenRepository.findAllById(ids)).thenReturn(kitchenEntities);

		List<Kitchen> kitchens = kitchenDataProvider.retrieveAllIds(ids);

		assertEquals(kitchenEntities.size(), kitchens.size());
		verify(kitchenRepository, times(1)).findAllById(ids);
	}

	@Test
	void create_shouldSaveAndReturnKitchen() {
		Kitchen kitchen = new Kitchen(1L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.DONE);
		KitchenEntity kitchenEntity = new KitchenEntity();
		kitchenEntity.setOrderId(1L);
		when(kitchenRepository.save(any())).thenReturn(kitchenEntity);

		Kitchen savedKitchen = kitchenDataProvider.create(kitchen);

		assertEquals(kitchenEntity.getOrderId(), savedKitchen.getOrderId());
		verify(kitchenRepository, times(1)).save(any());
	}

	@Test
	void update_shouldSaveAndReturnKitchen() {
		Kitchen kitchen = new Kitchen(1L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.IN_PRODUCTION);
		KitchenEntity kitchenEntity = new KitchenEntity();
		kitchenEntity.setOrderId(1L);
		when(kitchenRepository.save(any())).thenReturn(kitchenEntity);

		Kitchen updatedKitchen = kitchenDataProvider.update(kitchen);

		assertEquals(kitchenEntity.getOrderId(), updatedKitchen.getOrderId());
		verify(kitchenRepository, times(1)).save(any());
	}

	@Test
	void retrieveById_shouldReturnKitchen() {
		KitchenEntity kitchenEntity = new KitchenEntity();
		kitchenEntity.setOrderId(1L);
		when(kitchenRepository.findById(1L)).thenReturn(Optional.of(kitchenEntity));

		Optional<Kitchen> kitchen = kitchenDataProvider.retrieveById(1L);

		assertTrue(kitchen.isPresent());
		assertEquals(kitchenEntity.getOrderId(), kitchen.get().getOrderId());
		verify(kitchenRepository, times(1)).findById(1L);
	}

}