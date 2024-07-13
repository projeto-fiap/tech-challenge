package tech.fiap.project.infra.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PaymentRequestDTO {
    @JsonProperty("cash_out")
    private CashOutDTO cashOut;
    private String description;
    @JsonProperty("external_reference")
    private String externalReference;
    private List<ItemDTO> items;
    @JsonProperty("notification_url")
    private String notificationUrl;
    private String title;
    @JsonProperty("total_amount")
    private BigDecimal totalAmount;




}