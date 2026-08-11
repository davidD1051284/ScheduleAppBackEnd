package com.example.scheduleApp.controller;

import com.example.scheduleApp.entity.Event;
import com.example.scheduleApp.entity.User;
import com.example.scheduleApp.Repository.UserRepository;
import com.example.scheduleApp.Service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

	@Autowired
	private EventService eventService;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/user/{userId}")
	public List<Event> getEventsByUser(@PathVariable("userId") Long userId) {

		return eventService.getEventsByUser(userId);
	}

	@GetMapping("/finishedEvent/{userId}")
	public List<Event> getFinishEventsByUser(@PathVariable("userId") Long userId) {

		return eventService.getFinishedEventsByUser(userId);
	}

	@GetMapping("/unfinishedEvent/{userId}")
	public List<Event> getUnfinishEventsByUser(@PathVariable("userId") Long userId) {

		return eventService.getUnfinishedEventsByUser(userId);
	}

	@GetMapping("/event/{eventId}")
	public ResponseEntity<Event> getEventContentByEventId(@PathVariable("eventId") Long eventId) {

		return eventService.getEventsContent(eventId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/create/{userId}")
	public Event createEvent(@PathVariable("userId") Long userId, @RequestBody Event event) {

		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("找不到使用者"));

		user.setEventCount(user.getEventCount() + 1);
		
		user.setUnfinishedCount(user.getUnfinishedCount() + 1);

		userRepository.save(user);

		event.setUser(user);

		System.out.println("eventDate = " + event.getEventDate());
		System.out.println("title = " + event.getTitle());

		return eventService.createEventsByUser(event);
	}

	@PutMapping("/edit/{eventId}")
	public Event editEvent(@PathVariable("eventId") Long eventId, @RequestBody Event event) {

		return eventService.editEventsByEventId(eventId, event);
	}

	@PostMapping("/finish/{eventId}")
	public Event finishEvent(@PathVariable("eventId") Long eventId) {

		return eventService.finishEventsByEventId(eventId);
	}

	@DeleteMapping("/delete/{eventId}")
	public void deleteEvent(@PathVariable("eventId") Long eventId) {

		eventService.deleteEventsByEventId(eventId);
	}
}