package com.event_app.Event_App.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "Massage")
@Data
public class Message {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User receiver;

    @Column(name = "mesaj_metni", columnDefinition = "TEXT")
    private String message_text;
    private LocalDateTime sendMessageTime;

}
