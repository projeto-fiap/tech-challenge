package tech.fiap.project.app.adapter;

import org.springframework.stereotype.Service;
import tech.fiap.project.app.dto.InstructionPaymentOrderDTO;
import tech.fiap.project.domain.entity.InstructionPaymentOrder;

@Service
public class InstructionPaymentOrderMapper {
    public InstructionPaymentOrderDTO toDTO(InstructionPaymentOrder instructionPaymentOrder) {
        return new InstructionPaymentOrderDTO(instructionPaymentOrder.getId(), instructionPaymentOrder.getDescription(), instructionPaymentOrder.getExternalReference(), instructionPaymentOrder.getTitle(), instructionPaymentOrder.getNotificationUrl(), instructionPaymentOrder.getQrData(), instructionPaymentOrder.getAmount());
    }

    public InstructionPaymentOrder toEntity(InstructionPaymentOrderDTO instructionPaymentOrderDTO) {
        return new InstructionPaymentOrder(instructionPaymentOrderDTO.getId(), instructionPaymentOrderDTO.getDescription(), instructionPaymentOrderDTO.getExternalReference(), instructionPaymentOrderDTO.getTitle(), instructionPaymentOrderDTO.getNotificationUrl(), instructionPaymentOrderDTO.getQrData(), instructionPaymentOrderDTO.getAmount());
    }
}
