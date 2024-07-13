package tech.fiap.project.infra.exception;

import org.springframework.http.HttpStatus;

public class PaymentNotFound extends BusinessException{
    public PaymentNotFound(String paymentId) {
        super( "payment.not.found", HttpStatus.NOT_FOUND,null, paymentId);
    }

}
