package tech.fiap.project.domain.usecase.order;

import tech.fiap.project.domain.entity.Order;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

public interface EndOrderUseCase {

	BufferedImage execute(Long id);

}
