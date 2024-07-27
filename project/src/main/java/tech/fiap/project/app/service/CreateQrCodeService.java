package tech.fiap.project.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.app.adapter.InstructionPaymentOrderMapper;
import tech.fiap.project.app.dto.InstructionPaymentOrderDTO;
import tech.fiap.project.domain.entity.InstructionPaymentOrder;
import tech.fiap.project.domain.usecase.CreatePaymentUrlUseCase;
import tech.fiap.project.domain.usecase.impl.GenerateQrCode;

import java.awt.image.BufferedImage;

@Service
@AllArgsConstructor
public class CreateQrCodeService {

	private CreatePaymentUrlUseCase createPaymentUrlUseCase;

	private InstructionPaymentOrderMapper orderMapper;

	private GenerateQrCode generateQrCode;

	public BufferedImage execute(InstructionPaymentOrderDTO instructionPaymentOrderDTO) {
		InstructionPaymentOrder instructionPaymentOrder = orderMapper.toEntity(instructionPaymentOrderDTO);
		String paymentUrl = createPaymentUrlUseCase.execute(instructionPaymentOrder);
		return generateQrCode.execute(paymentUrl);
	}

}
