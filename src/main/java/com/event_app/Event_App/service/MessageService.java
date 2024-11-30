package com.event_app.Event_App.service;

import com.event_app.Event_App.entity.Message;
import com.event_app.Event_App.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // Tüm mesajları al
    public List<Message> getAllMessages(Optional<Long> senderId, Optional<Long> receiverId) {
        if (senderId.isPresent() && receiverId.isPresent()) {
            return messageRepository.findBySenderIdAndReceiverId(senderId.get(), receiverId.get());
        } else if (senderId.isPresent()) {
            return messageRepository.findBySenderId(senderId.get());
        } else if (receiverId.isPresent()) {
            return messageRepository.findByReceiverId(receiverId.get());
        }
        return messageRepository.findAll();
    }

    // Yeni bir mesaj oluştur
    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    // Mesajı id ile al
    public Message getMessageById(Long messageId) {
        return messageRepository.findById(messageId).orElse(null);
    }

    // Mesajı güncelle
    public Message updateMessage(Long messageId, Message newMessage) {
        Optional<Message> message = messageRepository.findById(messageId);
        if (message.isPresent()) {
            Message foundMessage = message.get();
            foundMessage.setSender(newMessage.getSender());
            foundMessage.setReceiver(newMessage.getReceiver());
            foundMessage.setMessage_text(newMessage.getMessage_text());
            foundMessage.setSendMessageTime(newMessage.getSendMessageTime());
            return messageRepository.save(foundMessage);
        } else {
            return null;
        }
    }

    // Mesajı sil
    public void deleteMessage(Long messageId) {
        messageRepository.deleteById(messageId);
    }
}
