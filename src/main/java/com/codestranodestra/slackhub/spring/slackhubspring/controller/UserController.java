package com.codestranodestra.slackhub.spring.slackhubspring.controller;

import com.codestranodestra.slackhub.spring.slackhubspring.model.User;
import com.codestranodestra.slackhub.spring.slackhubspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

import static org.springframework.http.HttpStatus.GONE;


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

    @PutMapping("/users/{userId}")
    public ResponseEntity<Void> updateUser(@RequestBody User updatedUser) {

        userService.updateUser(updatedUser);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(updatedUser.getUserId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Integer userId) {
        userService.deleteUserById(userId);

//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .buildAndExpand().toUri();
//
//        return ResponseEntity.created(location).build();

        return ResponseEntity.status(GONE).build();
    }

}