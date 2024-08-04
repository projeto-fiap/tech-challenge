package tech.fiap.project.app.service.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.adapter.OrderMapper;
import tech.fiap.project.app.dto.OrderDTO;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.usecase.order.CreateOrUpdateOrderUseCase;

@Service
@AllArgsConstructor
public class CreateOrderService {

	private CreateOrUpdateOrderUseCase createOrUpdateOrderUsecase;

	public OrderDTO execute(OrderDTO orderDTO) {
		Order order = createOrUpdateOrderUsecase.execute(OrderMapper.toDomain(orderDTO));
		return OrderMapper.toDTO(order);
	}

}
