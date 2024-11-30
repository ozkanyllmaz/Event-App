package com.event_app.Event_App.service;

import com.event_app.Event_App.entity.User;
import com.event_app.Event_App.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();  // BCrypt encoder'ı başlatıyoruz
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveOneUser(User newUser) {
        return userRepository.save(newUser);  // Şifreyi olduğu gibi kaydediyoruz
    }


    public User getOneUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateOneUser(Long userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());  // Şifre güncellemesi yapılacaksa şifreyi de şifreleyebiliriz
            foundUser.setEmail(newUser.getEmail());
            foundUser.setLocation(newUser.getLocation());
            foundUser.setInterests(newUser.getInterests());
            foundUser.setFirstName(newUser.getFirstName());
            foundUser.setLastName(newUser.getLastName());
            foundUser.setBirthDate(newUser.getBirthDate());
            foundUser.setPhoneNumber(newUser.getPhoneNumber());
            foundUser.setProfilePhoto(newUser.getProfilePhoto());
            foundUser.setGender(newUser.getGender());
            userRepository.save(foundUser);
            return foundUser;
        } else {
            return null;
        }
    }

    public void deleteOneUser(Long userid) {
        userRepository.deleteById(userid);
    }

    public User loginUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUserName(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Şifreyi düz metin olarak karşılaştırıyoruz
            if (password.equals(user.getPassword())) {  // bcrypt kullanmıyorsanız, doğrudan metin karşılaştırması
                return user;  // Şifre doğruysa, kullanıcıyı döndürüyoruz
            } else {
                return null;  // Şifre yanlış
            }
        }
        return null;  // Kullanıcı adı bulunamadı
    }



}