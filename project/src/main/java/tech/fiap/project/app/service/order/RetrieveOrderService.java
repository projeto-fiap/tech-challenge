package tech.fiap.project.app.service.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.adapter.OrderMapper;
import tech.fiap.project.app.dto.OrderDTO;
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

	public List<OrderDTO> findAll() {
		List<OrderDTO> dto = OrderMapper.toDTO(retrieveOrderUseCase.findAll());
		dto.forEach(this::setDuration);
		return dto;
	}

	public Optional<OrderDTO> findById(Long id) {
		Optional<OrderDTO> orderDTO = retrieveOrderUseCase.findById(id).map(OrderMapper::toDTO);
		orderDTO.ifPresent(this::setDuration);
		return orderDTO;
	}

	private void setDuration(OrderDTO order) {
		long seconds = Duration.between(order.getCreatedDate(), LocalDateTime.now()).getSeconds();
		order.setAwaitingTime(Duration.of(seconds, ChronoUnit.SECONDS));
	}

}
