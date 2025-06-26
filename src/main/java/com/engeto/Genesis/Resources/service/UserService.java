package com.engeto.Genesis.Resources.service;

import com.engeto.Genesis.Resources.dto.UserDTO;
import com.engeto.Genesis.Resources.mapper.UserMapper;
import com.engeto.Genesis.Resources.model.User;
import com.engeto.Genesis.Resources.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;

    public UserService(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public List<UserDTO> getAllUsers(boolean detail) {
        return userRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDTO getUser(Long id, boolean detail) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return mapper.toDto(user);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = mapper.toEntity(userDTO);
        return mapper.toDto(userRepository.save(user));
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User user = mapper.toEntity(userDTO);
        return mapper.toDto(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

