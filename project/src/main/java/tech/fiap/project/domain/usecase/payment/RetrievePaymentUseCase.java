package tech.fiap.project.domain.usecase.payment;

import tech.fiap.project.domain.entity.Payment;

import java.util.List;
import java.util.Optional;

public interface RetrievePaymentUseCase {
    List<Payment> findAll();
    Optional<Payment> findById(Long id);

}
