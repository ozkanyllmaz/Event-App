package com.event_app.Event_App.repository;

import com.event_app.Event_App.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findByEventId(Long eventId);

    List<Participant> findByUserId(Long userId);

    List<Participant> findByUserIdAndEventId(Long userId, Long eventId);
}
