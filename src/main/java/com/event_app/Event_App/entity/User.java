package com.event_app.Event_App.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "User")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String password;
    private String email;
    private String location;
    private String interests;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String phoneNumber;

    @Lob
    private String profilePhoto;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public enum Gender{
        ERKEK,KADIN
    }
}
