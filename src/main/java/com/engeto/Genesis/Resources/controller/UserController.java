package com.engeto.Genesis.Resources.controller;

import com.engeto.Genesis.Resources.dto.CreateUserRequest;
import com.engeto.Genesis.Resources.model.User;
import com.engeto.Genesis.Resources.service.UserService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserRequest request) throws IOException {
        User user = userService.create(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll(@RequestParam(defaultValue = "false") boolean detail) {
        return ResponseEntity.ok(userService.getAll(detail));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOne(@PathVariable Long id, @RequestParam(defaultValue = "false") boolean detail) {
        return ResponseEntity.ok(userService.getOne(id, detail));
    }

    @PutMapping
    public ResponseEntity<User> update(@Valid @RequestBody User updateRequest) {
        return ResponseEntity.ok(userService.update(updateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}