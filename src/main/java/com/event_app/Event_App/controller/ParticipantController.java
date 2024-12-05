package com.event_app.Event_App.controller;

import com.event_app.Event_App.entity.Participant;
import com.event_app.Event_App.service.ParticipantService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Participant> createParticipant(@RequestBody Participant participant) {
        Participant createdParticipant = participantService.createParticipant(participant);
        return ResponseEntity.ok(createdParticipant);  // HTTP 200 OK ile döner
    }

    // Katılımcıyı id ile al
    @GetMapping("/{participantId}")
    public ResponseEntity<Participant> getParticipant(@PathVariable Long participantId) {
        Participant participant = participantService.getParticipantById(participantId);
        if (participant == null) {
            return ResponseEntity.notFound().build();  // HTTP 404 Not Found
        }
        return ResponseEntity.ok(participant);
    }

    // Katılımcıyı güncelle
    @PutMapping("/{participantId}")
    public ResponseEntity<Participant> updateParticipant(@PathVariable Long participantId, @RequestBody Participant newParticipant) {
        Participant updatedParticipant = participantService.updateParticipant(participantId, newParticipant);
        if (updatedParticipant == null) {
            return ResponseEntity.notFound().build();  // HTTP 404 Not Found
        }
        return ResponseEntity.ok(updatedParticipant);
    }

    // Katılımcıyı sil
    @DeleteMapping("/{participantId}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long participantId) {
        if (participantService.getParticipantById(participantId) == null) {
            return ResponseEntity.notFound().build();  // Katılımcı bulunamazsa 404 döner
        }
        participantService.deleteParticipant(participantId);
        return ResponseEntity.noContent().build();  // HTTP 204 No Content
    }
}
