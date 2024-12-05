package com.event_app.Event_App.service;

import com.event_app.Event_App.entity.User;
import com.event_app.Event_App.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    // Kullanıcının kullanıcı adına göre bilgilerini dönen servis metodu
    public User getUserByUsername(String username) {
        return userRepository.findByUserName(username).orElse(null);
    }

    @Transactional
    public User updateUserProfile(Long userId, User updatedProfile) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            user.setUserName(updatedProfile.getUserName());
            user.setProfilePhoto(updatedProfile.getProfilePhoto());
            user.setEmail(updatedProfile.getEmail());
            user.setLocation(updatedProfile.getLocation());
            user.setBirthDate(updatedProfile.getBirthDate());
            user.setFirstName(updatedProfile.getFirstName());
            user.setLastName(updatedProfile.getLastName());
            user.setPassword(updatedProfile.getPassword());

            // Güncellenmiş veriyi kaydediyoruz
            return userRepository.save(user);
        } else {
            return null;  // Kullanıcı bulunamadıysa null döner
        }
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }


    public User findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null); // Kullanıcı bulunamazsa null döner
    }
}
