package com.example.scheduleApp.controller;

import com.example.scheduleApp.entity.Event;
import com.example.scheduleApp.Service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

	@Autowired
	private EventService eventService;

	@GetMapping("/user/{userId}")
	public List<Event> getEventsByUser(@PathVariable("userId") Long userId) {

		return eventService.getEventsByUser(userId);
	}
}