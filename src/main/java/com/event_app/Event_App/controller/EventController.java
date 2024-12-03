package com.event_app.Event_App.controller;

import com.event_app.Event_App.entity.Event;
import com.event_app.Event_App.service.EventService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "https://localhost:3000")
@Data
public class EventController {
    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getAllEvent(@RequestParam Optional<Long> userId) {
        return eventService.getAllEvent(userId);
    }

    @PostMapping
    public Event createOneEvent(@RequestBody Event newEvent) {
        return eventService.createOneEvent(newEvent);
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

    @GetMapping("/events/{eventId}")
    public Event getEventDetails(@PathVariable Long id) {
        return eventService.getEventDetails(id);
    }


}
