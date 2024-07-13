package tech.fiap.project.infra.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class WalletPaymentDTO {

    private BigDecimal transactionAmount;
    private String description;
    private String externalReference;
    private DiscountDTO discount;
}