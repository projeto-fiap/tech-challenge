package tech.fiap.project.app.service.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.adapter.OrderMapper;
import tech.fiap.project.app.dto.OrderRequestDTO;
import tech.fiap.project.app.dto.OrderResponseDTO;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.usecase.order.CreateOrUpdateOrderUseCase;

@Service
@AllArgsConstructor
public class CreateOrderService {

	private CreateOrUpdateOrderUseCase createOrUpdateOrderUsecase;

	public OrderResponseDTO execute(OrderRequestDTO orderRequestDTO) {
		orderRequestDTO.setId(null);
		Order order = createOrUpdateOrderUsecase.execute(OrderMapper.toDomain(orderRequestDTO));
		return OrderMapper.toDTO(order);
	}

}
