package com.codestranodestra.slackhub.spring.slackhubspring.controller;

import com.codestranodestra.slackhub.spring.slackhubspring.model.Message;
import com.codestranodestra.slackhub.spring.slackhubspring.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

import static org.springframework.http.HttpStatus.ACCEPTED;


@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages")
    public Collection<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{messageId}")
    public Message getMessaegById(@PathVariable Integer messageId) {
        return messageService.getMessageById(messageId);
    }


    @PostMapping("/messages")
    public ResponseEntity<Void> createMessage(@RequestBody Message newMessage) {

        messageService.createMessge(newMessage);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{messageId}").buildAndExpand(newMessage.getMessageId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/messages/{messageId}")
    public ResponseEntity<Void> updateMessage(@RequestBody Message updatedMessage) {

        messageService.updateMessage(updatedMessage);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(updatedMessage.getMessageId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Integer messageId) {
        messageService.deleteMessageById(messageId);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand().toUri();

        return ResponseEntity.created(location).build();
    }

}