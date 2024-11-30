package com.event_app.Event_App.controller;

import com.event_app.Event_App.entity.Message;
import com.event_app.Event_App.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // Tüm mesajları al (gönderen ya da alıcı ID ile filtreleme yapılabilir)
    @GetMapping
    public List<Message> getAllMessages(@RequestParam Optional<Long> senderId, @RequestParam Optional<Long> receiverId) {
        return messageService.getAllMessages(senderId, receiverId);
    }

    // Yeni bir mesaj oluştur
    @PostMapping
    public Message createMessage(@RequestBody Message message) {
        return messageService.createMessage(message);
    }

    // Mesajı id ile al
    @GetMapping("/{messageId}")
    public Message getMessage(@PathVariable Long messageId) {
        return messageService.getMessageById(messageId);
    }

    // Mesajı güncelle
    @PutMapping("/{messageId}")
    public Message updateMessage(@PathVariable Long messageId, @RequestBody Message newMessage) {
        return messageService.updateMessage(messageId, newMessage);
    }

    // Mesajı sil
    @DeleteMapping("/{messageId}")
    public void deleteMessage(@PathVariable Long messageId) {
        messageService.deleteMessage(messageId);
    }
}
