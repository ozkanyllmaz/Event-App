package com.event_app.Event_App.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Event")
@Data
public class Event {
    @Id
    private Long id;
    private String eventName;
    private String description;
    private LocalDate date;
    private LocalTime time;
    private int duration; // etkinlik süresi
    private String location;

    @ManyToOne
    @JoinColumn(name = "user_id")  // Bu, etkinliklerin hangi kullanıcıya ait olduğunu belirler
    private User user; // Etkinliği bir kullanıcıya bağlamak için

    @Enumerated(EnumType.STRING)
    private Category category;
    public enum Category {
        KONSER,
        ATOLYE_CALISMASI,
        SEMINER,
        PARTI,
        SERGI,
        TIYATRO,
        SINEMA,
        SPOR,
        FESTIVAL,
        EGITIM,
        TOPLANTI,
        KONFERANS,
        FUAR,
        DOGA_YURUYUSU,
        YARISMA,
        DIGER
    }


}
