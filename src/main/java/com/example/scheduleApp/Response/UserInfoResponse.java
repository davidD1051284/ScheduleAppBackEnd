package com.example.scheduleApp.Response;

public class UserInfoResponse {

	private Long id;
	private String userAccount;
	private String username;
	private int eventCount;
	private int finishedCount;
	private int unfinishedCount;

	public UserInfoResponse(Long id, String userAccount, String username, int eventCount, int finishedCount,
			int unfinishedCount) {
		super();
		this.id = id;
		this.userAccount = userAccount;
		this.username = username;
		this.eventCount = eventCount;
		this.finishedCount = finishedCount;
		this.unfinishedCount = unfinishedCount;
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
