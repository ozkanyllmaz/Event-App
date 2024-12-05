package com.event_app.Event_App.service;

import com.event_app.Event_App.entity.Event;
import com.event_app.Event_App.entity.User;
import com.event_app.Event_App.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private EventRepository eventRepository;
    private final UserService userService;

    public EventService(EventRepository eventRepository, UserService userService){
        this.eventRepository = eventRepository;
        this.userService = userService;
    }


    public List<Event> getAllEvent(Optional<Long> userId) {
        if(userId.isPresent()){
            return eventRepository.findByUserId(userId.get());
        }
        return eventRepository.findAll();
    }

    public Event getOneEventById(Long eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }

    public Event createOneEvent(Event newEvent) {
        return eventRepository.save(newEvent);
    }

    public Event updateOneEventById(Long eventId, Event newEvent) {
        Optional<Event> event = eventRepository.findById(eventId);
        if(event.isPresent()) {
            Event foundEvent = event.get();
            foundEvent.setEventName(newEvent.getEventName());
            foundEvent.setDescription(newEvent.getDescription());
            foundEvent.setDate(newEvent.getDate());
            foundEvent.setTime(newEvent.getTime());
            foundEvent.setDuration(newEvent.getDuration());
            foundEvent.setLocation(newEvent.getLocation());
            foundEvent.setCategory(newEvent.getCategory());
            eventRepository.save(foundEvent);
            return foundEvent;
        } else{
            return null;
        }
    }

    public void deleteOneEventById(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    public List<Event> getEventsByUserId(Long userId) {
        return eventRepository.findByUserId(userId);
    }

    public Event createEvent(Event event, Long userId) {
        if (userId != null) {
            User user = userService.findById(userId);
            if (user == null) {
                return null; // Kullanıcı bulunamadı
            }
            event.setUser(user); // Kullanıcıyı etkinliğe bağla
        }
        return eventRepository.save(event);
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }
}
