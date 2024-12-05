package com.event_app.Event_App.controller;

import com.event_app.Event_App.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.event_app.Event_App.entity.User;
import com.event_app.Event_App.service.UserService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

@CrossOrigin(origins = "http://localhost:3000")  // Frontend portuna izin veriyoruz
@RestController
@RequestMapping("/users")
@Data
public class UserController {
    private final UserService userService;
    private final EmailService emailService; // EmailService alanı

    public UserController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService; // EmailService enjekte ediliyor
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

    // Backend (Spring Boot örneği)
    @GetMapping("/users/profile")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String username) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(user);
    }




    @GetMapping("/profile/{userId}")
    public ResponseEntity<User> getUserProfileById(@PathVariable Long userId) {
        User user = userService.getOneUser(userId);
        if (user != null) {
            return ResponseEntity.ok(user);  // Kullanıcı bulundu, bilgilerini döndürüyoruz
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Kullanıcı bulunamadı
        }
    }



    @PutMapping("/profile/{userId}")
    public User updateUserProfile(@PathVariable Long userId, @RequestBody User updatedProfile) {
        return userService.updateUserProfile(userId, updatedProfile);
    }



    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> emailRequest) {
        String email = emailRequest.get("email");
        User user = userService.getUserByEmail(email);

        if (user == null) {
            // Kullanıcı bulunamazsa hata mesajını JSON olarak gönderiyoruz
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Email not found"));
        }

        String newPassword = generateRandomCode(4);
        user.setPassword(newPassword); // Şifreyi doğrudan set et (şifreleme eklenecekse bcrypt kullanın)
        userService.saveOneUser(user);

        // Mail gönderimi
        try {
            emailService.sendSimpleMessage(
                    email,
                    "Password Reset",
                    "Your new password is: " + newPassword
            );
        } catch (Exception e) {
            // E-posta gönderiminde bir hata oluşursa, JSON formatında hata mesajı dönüyoruz
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Email sending failed"));
        }

        return ResponseEntity.ok("Password reset email sent");
    }


    // 4 Haneli Şifre Üretme Metodu
    private String generateRandomCode(int length) {
        Random random = new Random();
        return String.format("%0" + length + "d", random.nextInt((int) Math.pow(10, length)));
    }






}
