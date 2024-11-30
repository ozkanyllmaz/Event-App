package com.event_app.Event_App.service;

import com.event_app.Event_App.entity.Participant;
import com.event_app.Event_App.repository.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    // Tüm katılımcıları al
    public List<Participant> getAllParticipants(Optional<Long> userId, Optional<Long> eventId) {
        if(userId.isPresent() && eventId.isPresent()){
            return participantRepository.findByUserIdAndEventId(userId.get(), eventId.get());
        } else if (userId.isPresent()) {
            return participantRepository.findByUserId(userId.get());
        } else if (eventId.isPresent()) {
            return participantRepository.findByEventId(eventId.get());
        }
        return participantRepository.findAll();
    }

    // Katılımcıyı oluştur
    public Participant createParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    // Katılımcıyı id ile al
    public Participant getParticipantById(Long participantId) {
        return participantRepository.findById(participantId).orElse(null);
    }

    // Katılımcıyı güncelle
    public Participant updateParticipant(Long participantId, Participant newParticipant) {
        Optional<Participant> participant = participantRepository.findById(participantId);
        if (participant.isPresent()) {
            Participant foundParticipant = participant.get();
            foundParticipant.setUser(newParticipant.getUser());
            foundParticipant.setEvent(newParticipant.getEvent());
            return participantRepository.save(foundParticipant);
        } else {
            return null;
        }
    }

    // Katılımcıyı sil
    public void deleteParticipant(Long participantId) {
        participantRepository.deleteById(participantId);
    }
}
