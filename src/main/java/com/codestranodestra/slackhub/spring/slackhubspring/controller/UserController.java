package com.codestranodestra.slackhub.spring.slackhubspring.controller;

import com.codestranodestra.slackhub.spring.slackhubspring.model.User;
import com.codestranodestra.slackhub.spring.slackhubspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Collection<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable Integer userId) {
        return userService.getUserById(userId);
    }


    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody User newUser) {

        userService.createUser(newUser);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{userId}").buildAndExpand(newUser.getUserId()).toUri();

        return ResponseEntity.created(location).build();
    }

//    @PutMapping("/users/{userId}/messages/{messageId}")
//    public ResponseEntity<Void> updateMessage(@PathVariable Integer userId,
//                                              @PathVariable Integer messageId,
//                                              @RequestBody Message newMessage) {
//
//        Message message = userService.updateMessageNew(userId, messageId, newMessage);
//
//        if (message == null)
//            return ResponseEntity.noContent().build();
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .buildAndExpand(message.getMessageId()).toUri();
//
//        return ResponseEntity.created(location).build();
//    }
//
//    @DeleteMapping("/users/{userId}/messages/{messageId}")
//    public ResponseEntity<Void> deleteMessage(@PathVariable Integer userId,
//                                              @PathVariable Integer messageId) {
//        Message message = userService.deleteMessage(userId, messageId);
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .buildAndExpand(message.getMessageId()).toUri();
//
//        return ResponseEntity.created(location).build();
//    }

}