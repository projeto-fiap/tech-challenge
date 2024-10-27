package tech.fiap.project.infra.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("mercado-pago")
@Configuration
@Getter
@Setter
public class MercadoPagoProperties {

	public String userId;

	public String pos;

	public String accessToken;

}
