package com.example.scheduleApp.Service;

import com.example.scheduleApp.entity.Event;
import com.example.scheduleApp.Repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	public List<Event> getEventsByUser(Long userId) {

		return eventRepository.findByUserId(userId);
	}
}