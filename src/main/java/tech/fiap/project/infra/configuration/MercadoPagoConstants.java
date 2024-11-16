package tech.fiap.project.infra.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@NoArgsConstructor
public class MercadoPagoConstants {

	public static final String BASE_URI = "https://api.mercadopago.com/";

	public static final String BASE_PAYMENT_METHOD = "/instore/orders/qr/seller/collectors/%s/pos/%s/qrs";

}
