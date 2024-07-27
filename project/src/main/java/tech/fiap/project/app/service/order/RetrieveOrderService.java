package tech.fiap.project.app.service.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.adapter.OrderMapper;
import tech.fiap.project.app.dto.OrderDTO;
import tech.fiap.project.domain.usecase.order.RetrieveOrderUseCase;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RetrieveOrderService {

	private RetrieveOrderUseCase retrieveOrderUseCase;

	public List<OrderDTO> findAll() {
		return OrderMapper.toDTO(retrieveOrderUseCase.findAll());
	}
	public Optional<OrderDTO> findById(Long id) {
		return retrieveOrderUseCase.findById(id).map(OrderMapper::toDTO);
	}

}
