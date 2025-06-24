package com.engeto.Genesis.Resources.service;

import com.engeto.Genesis.Resources.dto.UserDTO;
import com.engeto.Genesis.Resources.mapper.UserMapper;
import com.engeto.Genesis.Resources.model.User;
import com.engeto.Genesis.Resources.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@UserServiceImpl
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public List<UserDTO> getAllUsers(boolean detail) {
        return userRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUser(Long id, boolean detail) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return mapper.toDto(user);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = mapper.toEntity(userDTO);
        return mapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User user = mapper.toEntity(userDTO);
        return mapper.toDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
