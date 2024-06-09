package com.martinosama.musespark.Controller;

import com.martinosama.musespark.DTO.UserDTO;
import com.martinosama.musespark.Entity.User;
import com.martinosama.musespark.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        logger.info("Attempting to register user: " + userDTO.getEmail());
        Optional<User> existingUser = userService.findByEmail(userDTO.getEmail());
        if (existingUser.isPresent()) {
            logger.warning("User already exists: " + userDTO.getEmail());
            return ResponseEntity.badRequest().body("User already exists");
        }
        try {
            User user = userService.createUser(userDTO);
            logger.info("User registered successfully: " + user.getEmail());
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.severe("Error occurred while registering user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed");
        }
    }

    @PostMapping("/signin")
    @ResponseBody
    public ResponseEntity<?> signInUser(@RequestBody UserDTO userDTO) {
        try {
            User user = userService.signInUser(userDTO.getEmail(), userDTO.getPassword());
            return ResponseEntity.ok(user);
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
