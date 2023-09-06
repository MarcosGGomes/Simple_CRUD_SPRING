package com.democrud2.cruddemo2.controllers;

import com.democrud2.cruddemo2.domain.user.RequestUser;
import com.democrud2.cruddemo2.domain.user.User;
import com.democrud2.cruddemo2.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class User_controller {
    @Autowired
    private UserRepository repository;
    @PostMapping
    public ResponseEntity registerUser(@RequestBody @Valid RequestUser data) {
        User newUser = new User(data);
        repository.save(newUser);
        return  ResponseEntity.ok(newUser);
    }
    @GetMapping
    public ResponseEntity getAllUsers() {
        Sort sort = Sort.by(Sort.Order.asc("id"));
        var allUser = repository.findAll(sort);
        return ResponseEntity.ok(allUser);
    }
    @PutMapping
    @Transactional
    public ResponseEntity UpdateUser(@RequestBody @Valid RequestUser data) {
        Optional<User> optionalUser = repository.findById(data.id());
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(data.name());
            user.setEmail(data.email());
            return ResponseEntity.ok("User Alterado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity deleteUser(@RequestBody @Valid RequestUser data) {
       Optional<User> optionalUser = repository.findById(data.id());
       if (optionalUser.isPresent()) {
           User user = optionalUser.get();
           repository.delete(user);
           return ResponseEntity.ok(user);
       } else {
           return ResponseEntity.notFound().build();
       }

    }

}
