package com.example.scheduleApp.controller;

import com.example.scheduleApp.entity.User;

import jakarta.servlet.http.HttpServletRequest;

import com.example.scheduleApp.Repository.UserRepository;
import com.example.scheduleApp.Request.LoginRequest;
import com.example.scheduleApp.Request.RegisterRequest;
import com.example.scheduleApp.Response.UserInfoResponse;
import com.example.scheduleApp.Service.AuthService;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private UserRepository userRepository;

	// =========================
	// POST /api/auth/register
	// =========================

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

		try {

			User user = authService.register(request);

			return ResponseEntity.ok(Map.of("message", "註冊成功", "userID", user.getId()));

		} catch (RuntimeException e) {

			return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
		}
	}

	// =========================
	// POST /api/auth/login
	// =========================

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletRequest httpRequest) {

		try {

			User user = authService.login(request);

			// 建立 Authentication
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					user.getUserAccount(), null, Collections.emptyList());

			// 建立 SecurityContext
			SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

			securityContext.setAuthentication(authentication);

			// 放入目前 Thread
			SecurityContextHolder.setContext(securityContext);

			// 保存到 Session
			httpRequest.getSession(true).setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

			return ResponseEntity.ok(Map.of("message", "登入成功", "id", user.getId(), "username", user.getUsername(),
					"eventCount", user.getEventCount(), "finishedCount", user.getFinishedCount(), "unfinishedCount",
					user.getUnfinishedCount()));

		} catch (RuntimeException e) {

			return ResponseEntity.status(401).body(Map.of("message", e.getMessage()));
		}
	}

	@GetMapping("/getUserInfo")
	public UserInfoResponse me(Authentication authentication) { // spring boot Authentication幫你保存data

		System.out.println("Authentication = " + authentication);

		if (authentication == null) {
			throw new RuntimeException("Authentication 是 null");
		}

		System.out.println("username = " + authentication.getName());

		String userAccount = authentication.getName();

		User user = userRepository.findByUserAccount(userAccount).orElseThrow();

		return new UserInfoResponse(user.getId(), user.getUserAccount(), user.getUsername(), user.getEventCount(),
				user.getFinishedCount(), user.getUnfinishedCount());
	}
}