package com.example.scheduleApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.cors(cors -> {
		}).csrf(csrf -> csrf.disable())

				.authorizeHttpRequests(auth -> auth
						//登入、註冊一開始就能用
						.requestMatchers("/api/auth/login", "/api/auth/register").permitAll()

						.anyRequest().authenticated())

				.logout(logout -> logout.logoutUrl("/api/auth/logout").invalidateHttpSession(true)
						.clearAuthentication(true).deleteCookies("JSESSIONID"));

		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration configuration = new CorsConfiguration();

		// React
		configuration.setAllowedOrigins(List.of("http://localhost:3000"));

		// HTTP methods
		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

		// Headers
		configuration.setAllowedHeaders(List.of("*"));

		// 如果未來使用 Cookie / Session
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		source.registerCorsConfiguration("/**", configuration);

		return source;
	}
}