package com.example.scheduleApp.Service;

import com.example.scheduleApp.entity.Event;
import com.example.scheduleApp.entity.User;
import com.example.scheduleApp.Repository.EventRepository;
import com.example.scheduleApp.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private UserRepository userRepository;

	public List<Event> getEventsByUser(Long userId) {

		return eventRepository.findByUserId(userId);
	}

	public List<Event> getFinishedEventsByUser(Long userId) {

		return eventRepository.findByUserIdAndCompletedTrue(userId);
	}

	public List<Event> getUnfinishedEventsByUser(Long userId) {

		return eventRepository.findByUserIdAndCompletedFalse(userId);
	}

	public Event createEventsByUser(Event event) {

		return eventRepository.save(event);
	}

	public Optional<Event> getEventsContent(Long eventId) {

		return eventRepository.findById(eventId);
	}

	public Event editEventsByEventId(Long eventId, Event event) {

		Event editingEvent = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("找不到事件"));

		editingEvent.setTitle(event.getTitle());
		editingEvent.setEventDate(event.getEventDate());
		editingEvent.setEventGroup(event.getEventGroup());
		editingEvent.setContent(event.getContent());
		editingEvent.setInform(event.getInform());
		editingEvent.setFocus(event.getFocus());

		return eventRepository.save(editingEvent);
	}

	public void deleteEventsByEventId(Long eventId) {

		User user = eventRepository.findById(eventId).map(event -> event.getUser()).orElse(null);

		user.setEventCount(user.getEventCount() - 1);

		Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("找不到事件"));

		if (event.getCompleted())
			user.setFinishedCount(user.getFinishedCount() - 1);
		else
			user.setUnfinishedCount(user.getUnfinishedCount() - 1);

		userRepository.save(user);

		eventRepository.delete(event);
	}

	public Event finishEventsByEventId(Long eventId) {

		User user = eventRepository.findById(eventId).map(event -> event.getUser()).orElse(null);

		user.setFinishedCount(user.getFinishedCount() + 1);

		user.setUnfinishedCount(user.getUnfinishedCount() - 1);

		userRepository.save(user);

		Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("找不到事件"));

		event.setCompleted(true);

		return eventRepository.save(event);
	}
}