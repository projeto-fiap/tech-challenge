package tech.fiap.project.domain.usecase.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class GenerateQrCodeUseCaseImplTest {

	@InjectMocks
	private GenerateQrCodeUseCaseImpl generateQrCodeUseCaseImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void execute_generatesQrCodeSuccessfully() {
		String barcodeText = "http://example.com";
		BufferedImage qrCodeImage = generateQrCodeUseCaseImpl.execute(barcodeText);
		assertNotNull(qrCodeImage);
	}

}