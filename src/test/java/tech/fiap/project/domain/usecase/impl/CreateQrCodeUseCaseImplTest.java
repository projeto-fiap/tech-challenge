package tech.fiap.project.domain.usecase.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.usecase.CreatePaymentUrlUseCase;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class CreateQrCodeUseCaseImplTest {

	@Mock
	private CreatePaymentUrlUseCase createPaymentUrlUseCase;

	@Mock
	private GenerateQrCodeUseCaseImpl generateQrCodeUseCaseImpl;

	@InjectMocks
	private CreateQrCodeUseCaseImpl createQrCodeUseCaseImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void execute_generatesQrCodeSuccessfully() {
		Order order = new Order(1L, null, null, null, null, null, null, null, null);
		String paymentUrl = "http://payment.url";
		BufferedImage qrCodeImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

		when(createPaymentUrlUseCase.execute(order)).thenReturn(paymentUrl);
		when(generateQrCodeUseCaseImpl.execute(paymentUrl)).thenReturn(qrCodeImage);

		BufferedImage result = createQrCodeUseCaseImpl.execute(order);

		assertNotNull(result);
	}

}