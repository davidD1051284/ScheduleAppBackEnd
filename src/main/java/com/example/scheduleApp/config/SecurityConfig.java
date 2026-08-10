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

		http
				// 啟用 CORS
				.cors(cors -> {
				})

				// REST API 暫時關閉 CSRF
				.csrf(csrf -> csrf.disable())

				// API 權限
				.authorizeHttpRequests(auth -> auth

						// 登入、註冊不用登入
						.requestMatchers("/api/auth/login", "/api/auth/register").permitAll()

						// 其他 API
						.anyRequest().authenticated());

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