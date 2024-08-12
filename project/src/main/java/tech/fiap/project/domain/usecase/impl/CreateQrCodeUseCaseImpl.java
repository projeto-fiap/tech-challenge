package tech.fiap.project.domain.usecase.impl;

import tech.fiap.project.domain.entity.Order;
import tech.fiap.project.domain.usecase.CreatePaymentUrlUseCase;
import tech.fiap.project.domain.usecase.CreateQrCodeUseCase;

import java.awt.image.BufferedImage;

public class CreateQrCodeUseCaseImpl implements CreateQrCodeUseCase {

	private CreatePaymentUrlUseCase createPaymentUrlUseCase;

	private GenerateQrCodeUseCaseImpl generateQrCodeUseCaseImpl;

	public CreateQrCodeUseCaseImpl(CreatePaymentUrlUseCase createPaymentUrlUseCase,
			GenerateQrCodeUseCaseImpl generateQrCodeUseCaseImpl) {
		this.createPaymentUrlUseCase = createPaymentUrlUseCase;
		this.generateQrCodeUseCaseImpl = generateQrCodeUseCaseImpl;
	}

	public BufferedImage execute(Order order) {
		String paymentUrl = createPaymentUrlUseCase.execute(order);
		return generateQrCodeUseCaseImpl.execute(paymentUrl);
	}

}
