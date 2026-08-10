package com.example.scheduleApp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_name")
	private String username;

	@Column(name = "user_account", nullable = false, unique = true)
	private String userAccount;

	@Column(name = "user_password", nullable = false)
	private String password;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
	}
}