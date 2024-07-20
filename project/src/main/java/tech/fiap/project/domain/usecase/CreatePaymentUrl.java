package tech.fiap.project.domain.usecase;

import tech.fiap.project.domain.entity.InstructionPaymentOrder;

public interface CreatePaymentUrl {

	String execute(InstructionPaymentOrder instructionPaymentOrder);

}
