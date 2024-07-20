package tech.fiap.project.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.adapter.OrderMapper;
import tech.fiap.project.app.dto.OrderDTO;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.usecase.RetrieveOrderUseCase;

import java.util.List;

@Service
@AllArgsConstructor
public class RetrieveOrderService {

    private RetrieveOrderUseCase retrieveOrderUseCase;
    public List<OrderDTO> findAll() {
        return OrderMapper.toDTO(retrieveOrderUseCase.findAll());
    }
}
