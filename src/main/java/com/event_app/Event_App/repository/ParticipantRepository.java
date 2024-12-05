package com.event_app.Event_App.repository;

import com.event_app.Event_App.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    // Kullanıcı ID'sine göre katılımcıları al
    List<Participant> findByUserId(Long userId);

    // Etkinlik ID'sine göre katılımcıları al
    List<Participant> findByEventId(Long eventId);

    // Kullanıcı ID ve Etkinlik ID'ye göre katılımcıları al
    List<Participant> findByUserIdAndEventId(Long userId, Long eventId);
}
