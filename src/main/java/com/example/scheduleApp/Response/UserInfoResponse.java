package com.example.scheduleApp.Response;

public class UserInfoResponse {

	private Long id;
	private String userAccount;
	private String username;

	public UserInfoResponse(Long id, String userAccount, String username) {
		this.id = id;
		this.userAccount = userAccount;
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public String getUsername() {
		return username;
	}
}
