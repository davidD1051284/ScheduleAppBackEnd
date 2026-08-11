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
	private String username = "user";

	@Column(name = "user_account", nullable = false, unique = true)
	private String userAccount;

	@Column(name = "user_password", nullable = false)
	private String password;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "event_count")
	private int eventCount = 0;

	@Column(name = "finish_count")
	private int finishedCount = 0;

	@Column(name = "unfinish_count")
	private int unfinishedCount = 0;

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

	public int getEventCount() {
		return eventCount;
	}

	public void setEventCount(int eventCount) {
		this.eventCount = eventCount;
	}

	public int getFinishedCount() {
		return finishedCount;
	}

	public void setFinishedCount(int finishedCount) {
		this.finishedCount = finishedCount;
	}

	public int getUnfinishedCount() {
		return unfinishedCount;
	}

	public void setUnfinishedCount(int unfinishedCount) {
		this.unfinishedCount = unfinishedCount;
	}

}