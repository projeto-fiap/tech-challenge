package tech.fiap.project.domain.usecase;

import java.awt.image.BufferedImage;

public interface GenerateQrCodeUseCase {
    BufferedImage execute(String barcodeText);
}
