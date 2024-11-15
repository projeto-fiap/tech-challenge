package tech.fiap.project.app.service.kitchen;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
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

@Service
@AllArgsConstructor
public class KitchenService {

	private KitchenRetrieveUseCase retrieveUseCase;

	private KitchenCreateUseCase createUseCase;

	private KitchenUpdateUseCase updateUseCase;

	public Optional<KitchenDTO> create(OrderResponseDTO order) {
		var now = LocalDateTime.now();
		var kitchen = new Kitchen(order.getId(), now, now, KitchenStatus.AWAITING_PRODUCTION);

		Kitchen createdKitchen = createUseCase.execute(kitchen);
		return Optional.ofNullable(KitchenMapper.toDTO(createdKitchen));
	}

	public Optional<KitchenDTO> setInProduction(Long id) {
		var now = LocalDateTime.now();
		Optional<Kitchen> kitchen = retrieveUseCase.findById(id);

		if (kitchen.isPresent()) {
			var safeKitchen = kitchen.get();
			safeKitchen.setStatus(KitchenStatus.IN_PRODUCTION);
			safeKitchen.setUpdatedDate(now);

			Kitchen updatedKitchen = updateUseCase.execute(safeKitchen);
			return Optional.ofNullable(KitchenMapper.toDTO(updatedKitchen));
		}

		return Optional.empty();
	}

	public Optional<KitchenDTO> setDone(Long id) {
		var now = LocalDateTime.now();
		Optional<Kitchen> kitchen = retrieveUseCase.findById(id);

		if (kitchen.isPresent()) {
			var safeKitchen = kitchen.get();
			safeKitchen.setStatus(KitchenStatus.DONE);
			safeKitchen.setUpdatedDate(now);

			Kitchen updatedKitchen = updateUseCase.execute(safeKitchen);
			return Optional.ofNullable(KitchenMapper.toDTO(updatedKitchen));
		}

		return Optional.empty();
	}

	public List<KitchenDTO> findAll() {
		return KitchenMapper.toDTO(retrieveUseCase.findAll());
	}

	public Optional<KitchenDTO> findById(Long id) {
		return retrieveUseCase.findById(id).map(KitchenMapper::toDTO);
	}

}
