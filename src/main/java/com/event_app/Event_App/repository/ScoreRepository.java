package com.event_app.Event_App.repository;

import com.event_app.Event_App.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    // Kullanıcıya ait tüm skorları almak için
    List<Score> findByUserId(Long userId);
}
