package com.event_app.Event_App.controller;

import com.event_app.Event_App.entity.Score;
import com.event_app.Event_App.service.ScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    // Tüm skorları al (kullanıcı ID ile filtreleme yapılabilir)
    @GetMapping
    public List<Score> getAllScores(@RequestParam Optional<Long> userId) {
        return scoreService.getAllScores(userId);
    }

    // Yeni bir skor oluştur
    @PostMapping
    public Score createScore(@RequestBody Score score) {
        return scoreService.createScore(score);
    }

    // Skoru id ile al
    @GetMapping("/{scoreId}")
    public Score getScore(@PathVariable Long scoreId) {
        return scoreService.getScoreById(scoreId);
    }

    // Skoru güncelle
    @PutMapping("/{scoreId}")
    public Score updateScore(@PathVariable Long scoreId, @RequestBody Score newScore) {
        return scoreService.updateScore(scoreId, newScore);
    }

    // Skoru sil
    @DeleteMapping("/{scoreId}")
    public void deleteScore(@PathVariable Long scoreId) {
        scoreService.deleteScore(scoreId);
    }
}
