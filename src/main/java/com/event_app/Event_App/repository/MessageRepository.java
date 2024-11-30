package com.event_app.Event_App.repository;

import com.event_app.Event_App.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    // Gönderen kullanıcının ID'si ile mesajları bulma
    List<Message> findBySenderId(Long senderId);

    // Alıcı kullanıcının ID'si ile mesajları bulma
    List<Message> findByReceiverId(Long receiverId);

    // Gönderen ve alıcıya göre mesajları bulma
    List<Message> findBySenderIdAndReceiverId(Long senderId, Long receiverId);
}
