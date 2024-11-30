package com.event_app.Event_App.repository;

import com.event_app.Event_App.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Kullanıcı adıyla kullanıcıyı bulma
    Optional<User> findByUserName(String userName);

}
