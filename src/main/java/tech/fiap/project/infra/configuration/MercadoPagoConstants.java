package tech.fiap.project.infra.configuration;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class MercadoPagoConstants {

	public final static String AUTENTICATION_URI = "/oauth/token";

	public final static String BASE_URI = "https://api.mercadopago.com/";

	public final static String BASE_PAYMENT_METHOD = "/instore/orders/qr/seller/collectors/%s/pos/%s/qrs";

}
