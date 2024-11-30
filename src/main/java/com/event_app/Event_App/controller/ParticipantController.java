package com.event_app.Event_App.controller;

import com.event_app.Event_App.entity.Participant;
import com.event_app.Event_App.service.ParticipantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    // Tüm katılımcıları al (userId veya eventId ile filtreleme yapılabilir)
    @GetMapping
    public List<Participant> getAllParticipants(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> eventId) {
        return participantService.getAllParticipants(userId, eventId);
    }

    // Yeni bir katılımcı oluştur
    @PostMapping
    public Participant createParticipant(@RequestBody Participant participant) {
        return participantService.createParticipant(participant);
    }

    // Katılımcıyı id ile al
    @GetMapping("/{participantId}")
    public Participant getParticipant(@PathVariable Long participantId) {
        return participantService.getParticipantById(participantId);
    }

    // Katılımcıyı güncelle
    @PutMapping("/{participantId}")
    public Participant updateParticipant(@PathVariable Long participantId, @RequestBody Participant newParticipant) {
        return participantService.updateParticipant(participantId, newParticipant);
    }

    // Katılımcıyı sil
    @DeleteMapping("/{participantId}")
    public void deleteParticipant(@PathVariable Long participantId) {
        participantService.deleteParticipant(participantId);
    }
}
