package com.event_app.Event_App.service;

import com.event_app.Event_App.entity.Score;
import com.event_app.Event_App.repository.ScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {
    private final ScoreRepository scoreRepository;

    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    // Tüm skorları al
    public List<Score> getAllScores(Optional<Long> userId) {
        if (userId.isPresent()) {
            return scoreRepository.findByUserId(userId.get());
        }
        return scoreRepository.findAll();
    }

    // Bir skoru id ile al
    public Score getScoreById(Long scoreId) {
        return scoreRepository.findById(scoreId).orElse(null);
    }

    // Yeni bir skor oluştur
    public Score createScore(Score score) {
        return scoreRepository.save(score);
    }

    // Skoru güncelle
    public Score updateScore(Long scoreId, Score newScore) {
        Optional<Score> score = scoreRepository.findById(scoreId);
        if (score.isPresent()) {
            Score foundScore = score.get();
            foundScore.setUser(newScore.getUser());
            foundScore.setScores(newScore.getScores());
            foundScore.setScoreWonDate(newScore.getScoreWonDate());
            return scoreRepository.save(foundScore);
        } else {
            return null;
        }
    }

    // Skoru sil
    public void deleteScore(Long scoreId) {
        scoreRepository.deleteById(scoreId);
    }
}
