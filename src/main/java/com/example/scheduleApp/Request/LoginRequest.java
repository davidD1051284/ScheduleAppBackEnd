package com.example.scheduleApp.Request;

public class LoginRequest {

	private String UserAccount;
	private String password;

	public String getUserAccount() {
		return UserAccount;
	}

	public void setUserAccount(String UserAccount) {
		this.UserAccount = UserAccount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
