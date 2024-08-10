package tech.fiap.project.app.service.kitchen;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.adapter.KitchenMapper;
import tech.fiap.project.app.dto.KitchenDTO;
import tech.fiap.project.app.dto.OrderDTO;
import tech.fiap.project.domain.entity.*;
import tech.fiap.project.domain.usecase.kitchen.KitchenCreateUseCase;
import tech.fiap.project.domain.usecase.kitchen.KitchenRetrieveUseCase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class KitchenService {

	private KitchenRetrieveUseCase retrieveUseCase;

	private KitchenCreateUseCase createUseCase;

	public Optional<KitchenDTO> create(OrderDTO order) {
		var now = LocalDateTime.now();
		var kitchen = new Kitchen(order.getId(), now, KitchenStatus.AWAITING_PRODUCTION);

		Kitchen createdKitchen = createUseCase.execute(kitchen);
		return Optional.ofNullable(KitchenMapper.toDTO(createdKitchen));
	}

	public List<KitchenDTO> findAll() {
		return KitchenMapper.toDTO(retrieveUseCase.findAll());
	}

	public Optional<KitchenDTO> findById(Long id) {
		return retrieveUseCase.findById(id).map(KitchenMapper::toDTO);
	}

}
