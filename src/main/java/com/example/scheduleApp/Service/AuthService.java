package com.example.scheduleApp.Service;

import com.example.scheduleApp.entity.User;
import com.example.scheduleApp.Repository.UserRepository;
import com.example.scheduleApp.Request.LoginRequest;
import com.example.scheduleApp.Request.RegisterRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	// =========================
	// 註冊
	// =========================

	public User register(RegisterRequest request) {

		if (userRepository.existsByUserAccount(request.getUserAccount())) {

			throw new RuntimeException("帳號已存在");
		}

		User user = new User();

		user.setUserAccount(request.getUserAccount());

		// BCrypt 加密
		user.setPassword(passwordEncoder.encode(request.getPassword()));

		return userRepository.save(user);
	}

	// =========================
	// 登入
	// =========================

	public User login(LoginRequest request) {

		User user = userRepository.findByUserAccount(request.getUserAccount())
				.orElseThrow(() -> new RuntimeException("帳號錯誤"));

		boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());

		if (!matches) {
			throw new RuntimeException("帳號或密碼錯誤");
		}

		return user;
	}
}