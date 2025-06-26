package com.engeto.Genesis.Resources.controller;

import com.engeto.Genesis.Resources.dto.UserDTO;
import com.engeto.Genesis.Resources.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers(@RequestParam(defaultValue = "false") boolean detail) {
        return userService.getAllUsers(detail);
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id,
                               @RequestParam(defaultValue = "false") boolean detail) {
        return userService.getUser(id, detail);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PutMapping
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
