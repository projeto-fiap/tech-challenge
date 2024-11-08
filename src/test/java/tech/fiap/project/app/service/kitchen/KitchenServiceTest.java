package tech.fiap.project.app.service.kitchen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.app.adapter.KitchenMapper;
import tech.fiap.project.app.dto.KitchenDTO;
import tech.fiap.project.app.dto.OrderResponseDTO;
import tech.fiap.project.domain.entity.Kitchen;
import tech.fiap.project.domain.entity.KitchenStatus;
import tech.fiap.project.domain.usecase.kitchen.KitchenCreateUseCase;
import tech.fiap.project.domain.usecase.kitchen.KitchenRetrieveUseCase;
import tech.fiap.project.domain.usecase.kitchen.KitchenUpdateUseCase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class KitchenServiceTest {

	@Mock
	private KitchenRetrieveUseCase retrieveUseCase;

	@Mock
	private KitchenCreateUseCase createUseCase;

	@Mock
	private KitchenUpdateUseCase updateUseCase;

	@InjectMocks
	private KitchenService kitchenService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void create_shouldReturnKitchenDTO() {
		OrderResponseDTO order = new OrderResponseDTO();
		order.setId(1L);
		Kitchen kitchen = new Kitchen(1L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.AWAITING_PRODUCTION);
		KitchenDTO expectedKitchenDTO = KitchenMapper.toDTO(kitchen);

		when(createUseCase.execute(any(Kitchen.class))).thenReturn(kitchen);

		Optional<KitchenDTO> result = kitchenService.create(order);

		assertTrue(result.isPresent());
		assertEquals(expectedKitchenDTO, result.get());
	}

	@Test
	void setInProduction_shouldReturnUpdatedKitchenDTO() {
		Long kitchenId = 1L;
		Kitchen kitchen = new Kitchen(kitchenId, LocalDateTime.now(), LocalDateTime.now(),
				KitchenStatus.AWAITING_PRODUCTION);
		Kitchen updatedKitchen = new Kitchen(kitchenId, LocalDateTime.now(), LocalDateTime.now(),
				KitchenStatus.IN_PRODUCTION);
		KitchenDTO expectedKitchenDTO = KitchenMapper.toDTO(updatedKitchen);

		when(retrieveUseCase.findById(kitchenId)).thenReturn(Optional.of(kitchen));
		when(updateUseCase.execute(any(Kitchen.class))).thenReturn(updatedKitchen);

		Optional<KitchenDTO> result = kitchenService.setInProduction(kitchenId);

		assertTrue(result.isPresent());
		assertEquals(expectedKitchenDTO, result.get());
	}

	@Test
	void setDone_shouldReturnUpdatedKitchenDTO() {
		Long kitchenId = 1L;
		Kitchen kitchen = new Kitchen(kitchenId, LocalDateTime.now(), LocalDateTime.now(),
				KitchenStatus.AWAITING_PRODUCTION);
		Kitchen updatedKitchen = new Kitchen(kitchenId, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.DONE);
		KitchenDTO expectedKitchenDTO = KitchenMapper.toDTO(updatedKitchen);

		when(retrieveUseCase.findById(kitchenId)).thenReturn(Optional.of(kitchen));
		when(updateUseCase.execute(any(Kitchen.class))).thenReturn(updatedKitchen);

		Optional<KitchenDTO> result = kitchenService.setDone(kitchenId);

		assertTrue(result.isPresent());
		assertEquals(expectedKitchenDTO, result.get());
	}

	@Test
	void findAll_shouldReturnListOfKitchenDTOs() {
		Kitchen kitchen = new Kitchen(1L, LocalDateTime.now(), LocalDateTime.now(), KitchenStatus.AWAITING_PRODUCTION);
		List<Kitchen> kitchens = List.of(kitchen);
		List<KitchenDTO> expectedKitchenDTOs = KitchenMapper.toDTO(kitchens);

		when(retrieveUseCase.findAll()).thenReturn(kitchens);

		List<KitchenDTO> result = kitchenService.findAll();

		assertEquals(expectedKitchenDTOs, result);
	}

	@Test
	void findById_shouldReturnKitchenDTO() {
		Long kitchenId = 1L;
		Kitchen kitchen = new Kitchen(kitchenId, LocalDateTime.now(), LocalDateTime.now(),
				KitchenStatus.AWAITING_PRODUCTION);
		KitchenDTO expectedKitchenDTO = KitchenMapper.toDTO(kitchen);

		when(retrieveUseCase.findById(kitchenId)).thenReturn(Optional.of(kitchen));

		Optional<KitchenDTO> result = kitchenService.findById(kitchenId);

		assertTrue(result.isPresent());
		assertEquals(expectedKitchenDTO, result.get());
	}

}