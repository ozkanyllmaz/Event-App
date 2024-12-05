package com.event_app.Event_App.controller;

import com.event_app.Event_App.entity.Event;
import com.event_app.Event_App.entity.User;
import com.event_app.Event_App.service.EventService;
import com.event_app.Event_App.service.UserService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "http://localhost:3000")
@Data
public class EventController {
    private EventService eventService;
    private UserService userService;

    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping
    public List<Event> getAllEvent(@RequestParam Optional<Long> userId) {
        return eventService.getAllEvent(userId);
    }

    // Login olan kullanıcıyla etkinlik oluşturma
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event, @RequestHeader(value = "userId", required = false) Long loggedInUserId) {
        if (loggedInUserId == null) {
            return ResponseEntity.badRequest().body(null); // Header eksikse hata döndür
        }

        User user = userService.findById(loggedInUserId);
        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }

        event.setUser(user);
        Event createdEvent = eventService.save(event);
        return ResponseEntity.ok(createdEvent);
    }



    @GetMapping("/{eventId}")
    public Event getOneEvent(@PathVariable Long eventId) {
        return eventService.getOneEventById(eventId);
    }

    @PutMapping("/{eventId}")
    public Event updateOneEvent(@PathVariable Long eventId, @RequestBody Event newEvent) {
        return eventService.updateOneEventById(eventId, newEvent);
    }

    @DeleteMapping("/{eventId}")
    public void deleteOneEvent(@PathVariable Long eventId) {
        eventService.deleteOneEventById(eventId);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Event>> getUserEvents(@PathVariable("userId") Long userId) {
        List<Event> events = eventService.getEventsByUserId(userId);
        return ResponseEntity.ok(events);
    }


}
