package com.event_app.Event_App.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.event_app.Event_App.entity.User;
import com.event_app.Event_App.service.UserService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")  // Frontend portuna izin veriyoruz
@RestController
@RequestMapping("/users")
@Data
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Kullanıcı kaydını gerçekleştirecek yeni endpoint
    @PostMapping("/register")
    public User registerUser(@RequestBody User newUser) {
        return userService.saveOneUser(newUser);
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId) {
        return userService.getOneUser(userId);
    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {
        return userService.updateOneUser(userId, newUser);
    }

    @DeleteMapping("/{userid}")
    public void deleteOneUser(@PathVariable Long userid) {
        userService.deleteOneUser(userid);
    }

    // Login endpoint'i
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        User user = userService.loginUser(loginRequest.getUserName(), loginRequest.getPassword());

        if (user != null) {
            return ResponseEntity.ok(user); // Giriş başarılı
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Kullanıcı adı veya şifre hatalı");
        }
    }
}
