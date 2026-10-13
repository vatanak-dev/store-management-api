package com.vdev.service;

import com.vdev.entity.User;
import com.vdev.enums.UserRole;
import com.vdev.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public void registerUser (String username, String password) {

        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists!");
        }

        User createdUser = new User();
        createdUser.setUsername(username);
        createdUser.setPassword(passwordEncoder.encode(password));
        UserRole role = UserRole.STAFF;
        createdUser.setUserRole(role);
        userRepository.save(createdUser);

    }

}
