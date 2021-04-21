package com.deviget.minesweeperapi.controller;

import com.deviget.minesweeperapi.model.User;
import com.deviget.minesweeperapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping ("/")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById( @PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
    @GetMapping("/{userName}")
    public ResponseEntity<?> getByUserName( @PathVariable String UserName) {
        return ResponseEntity.ok(userService.getByUserName(UserName));
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser( @PathVariable Long userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }



}
