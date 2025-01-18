package tech.fiap.project.domain.usecase.order;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface EndOrderUseCase {

	BufferedImage execute(Long id) throws IOException;

}
