package tech.fiap.project.app.service.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.domain.usecase.order.EndOrderUseCase;

import java.awt.image.BufferedImage;

@Service
@AllArgsConstructor
public class EndOrderService {

	private EndOrderUseCase endOrderUseCase;

	public BufferedImage execute(Long id) {
		return endOrderUseCase.execute(id);
	}

}
