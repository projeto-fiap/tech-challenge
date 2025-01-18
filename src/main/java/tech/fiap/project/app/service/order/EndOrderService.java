package tech.fiap.project.app.service.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.domain.usecase.order.EndOrderUseCase;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
@AllArgsConstructor
public class EndOrderService {

	private EndOrderUseCase endOrderUseCase;

	public BufferedImage execute(Long id) throws IOException {
		return endOrderUseCase.execute(id);
	}

}
