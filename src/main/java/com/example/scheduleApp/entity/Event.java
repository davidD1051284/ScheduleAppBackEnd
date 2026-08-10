package com.example.scheduleApp.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@Column(name = "event_date")
	private LocalDateTime eventDate;

	@Column(name = "event_group")
	private String eventGroup;

	@Column(name = "content")
	private String content;

	@Column(name = "inform")
	private Boolean inform;

	@Column(name = "focus")
	private Boolean focus;

	@Column(name = "completed")
	private Boolean completed;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDateTime eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventGroup() {
		return eventGroup;
	}

	public void setEventGroup(String eventGroup) {
		this.eventGroup = eventGroup;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getInform() {
		return inform;
	}

	public void setInform(Boolean inform) {
		this.inform = inform;
	}

	public Boolean getFocus() {
		return focus;
	}

	public void setFocus(Boolean focus) {
		this.focus = focus;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
