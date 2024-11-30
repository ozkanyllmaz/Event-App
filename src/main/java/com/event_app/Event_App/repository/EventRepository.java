package com.event_app.Event_App.repository;

import com.event_app.Event_App.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByUserId(Long userId);
}
