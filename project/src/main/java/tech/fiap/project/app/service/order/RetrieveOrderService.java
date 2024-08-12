package tech.fiap.project.app.service.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.adapter.OrderMapper;
import tech.fiap.project.app.dto.OrderRequestDTO;
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

	public List<OrderRequestDTO> findAll() {
		List<OrderRequestDTO> dto = OrderMapper.toDTO(retrieveOrderUseCase.findAll());
		dto.forEach(this::setDuration);
		return dto;
	}

	public Optional<OrderRequestDTO> findById(Long id) {
		Optional<OrderRequestDTO> orderDTO = retrieveOrderUseCase.findById(id).map(OrderMapper::toDTO);
		orderDTO.ifPresent(this::setDuration);
		return orderDTO;
	}

	private void setDuration(OrderRequestDTO order) {
		long seconds = Duration.between(order.getCreatedDate(), LocalDateTime.now()).getSeconds();
		order.setAwaitingTime(Duration.of(seconds, ChronoUnit.SECONDS));
	}

}
