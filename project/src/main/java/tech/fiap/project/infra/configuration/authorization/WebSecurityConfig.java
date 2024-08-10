package tech.fiap.project.infra.configuration.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	private final CustomPersonDetailsService userDetailsService;

	@Autowired
	public WebSecurityConfig(CustomPersonDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests().requestMatchers(HttpMethod.POST, "/api/v1/person").permitAll()
				.requestMatchers(HttpMethod.POST, "/api/v1/person/admin").hasRole("ADMIN").anyRequest().authenticated()
				.and().csrf().disable().httpBasic();
		return http.build();
	}

}