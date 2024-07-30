package tech.fiap.project.domain.usecase;

import tech.fiap.project.domain.entity.Order;

import java.awt.image.BufferedImage;

public interface CreateQrCodeUseCase {

    BufferedImage execute(Order order);
}
