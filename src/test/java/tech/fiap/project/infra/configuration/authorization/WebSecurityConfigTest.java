// package tech.fiap.project.infra.configuration.authorization;
//
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import
// org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import
// org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.verify;
//
// @SpringBootTest
// class WebSecurityConfigTest {
//
// @Autowired
// private WebSecurityConfig webSecurityConfig;
//
// @Test
// void testFilterChain() throws Exception {
// HttpSecurity http = mock(HttpSecurity.class);
// JwtAuthenticationFilter jwtAuthenticationFilter = mock(JwtAuthenticationFilter.class);
//
// SecurityFilterChain filterChain = webSecurityConfig.filterChain(http,
// jwtAuthenticationFilter);
//
// assertNotNull(filterChain);
// }
//
// }