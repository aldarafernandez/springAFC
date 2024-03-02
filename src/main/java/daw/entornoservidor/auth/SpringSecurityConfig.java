package daw.entornoservidor.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import daw.entornoservidor.auth.filters.JwtAuthenticationFilter;
import daw.entornoservidor.auth.filters.JwtValidationFilter;

@Configuration
public class SpringSecurityConfig {
	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager() throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		return http.authorizeHttpRequests()
				.requestMatchers(HttpMethod.GET, "/api/product/all").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/product/{id}").permitAll()
				.requestMatchers(HttpMethod.POST, "/api/user/new").permitAll()
				.anyRequest().authenticated()
				.and()
				.addFilter(new JwtAuthenticationFilter(authenticationManager()))
				.addFilter(new JwtValidationFilter(authenticationManager()))
				.csrf((config -> config.disable()))
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.build();
	}
}
