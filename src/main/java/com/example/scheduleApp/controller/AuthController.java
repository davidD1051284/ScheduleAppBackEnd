package com.example.scheduleApp.controller;

import com.example.scheduleApp.entity.User;
import com.example.scheduleApp.Request.LoginRequest;
import com.example.scheduleApp.Request.RegisterRequest;
import com.example.scheduleApp.Service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	// =========================
	// POST /api/auth/register
	// =========================

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

		try {

			User user = authService.register(request);

			return ResponseEntity.ok(Map.of("message", "註冊成功", "username", user.getUsername()));

		} catch (RuntimeException e) {

			return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
		}
	}

	// =========================
	// POST /api/auth/login
	// =========================

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {

		try {

			User user = authService.login(request);

			return ResponseEntity.ok(Map.of("message", "登入成功", "userId", user.getId(), "username", user.getUsername()));

		} catch (RuntimeException e) {

			return ResponseEntity.status(401).body(Map.of("message", e.getMessage()));
		}
	}
}