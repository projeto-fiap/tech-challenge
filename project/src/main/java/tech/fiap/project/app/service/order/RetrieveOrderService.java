package tech.fiap.project.app.service.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.adapter.OrderMapper;
import tech.fiap.project.app.dto.OrderRequestDTO;
import tech.fiap.project.app.dto.OrderResponseDTO;
import tech.fiap.project.domain.usecase.order.RetrieveOrderUseCase;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RetrieveOrderService {

	private RetrieveOrderUseCase retrieveOrderUseCase;

	public List<OrderResponseDTO> findAll() {
		List<OrderResponseDTO> dto = OrderMapper.toDTO(retrieveOrderUseCase.findAll());
		dto.forEach(this::setDuration);
		return dto;
	}

	public Optional<OrderResponseDTO> findById(Long id) {
		Optional<OrderResponseDTO> orderDTO = retrieveOrderUseCase.findById(id).map(OrderMapper::toDTO);
		orderDTO.ifPresent(this::setDuration);
		return orderDTO;
	}

	private void setDuration(OrderResponseDTO order) {
		long seconds = Duration.between(order.getCreatedDate(), LocalDateTime.now()).getSeconds();
		order.setAwaitingTime(Duration.of(seconds, ChronoUnit.SECONDS));
	}

}
