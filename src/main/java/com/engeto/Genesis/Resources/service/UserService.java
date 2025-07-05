package com.engeto.Genesis.Resources.service;

import com.engeto.Genesis.Resources.dto.CreateUserRequest;
import com.engeto.Genesis.Resources.model.User;
import com.engeto.Genesis.Resources.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Data
public class UserService {

    @Autowired
    private UserRepository repository;


    public User create(CreateUserRequest request) throws IOException {

        if (repository.existsByPersonId(request.getPersonId())) {
            throw new IllegalArgumentException("PersonID už existuje.");
        }

        if (!getValidPersonIds().contains(request.getPersonId())) {
            throw new IllegalArgumentException("Neplatné PersonID.");
        }

        String uuid = UUID.randomUUID().toString();
        User user = new User(null, request.getName(), request.getSurname(), request.getPersonId(), uuid);
        repository.save(user);
        return user;
    }

    public List<User> getAll(boolean detail) {
        return repository.findAll(detail);
    }

    public User getOne(Long id, boolean detail) {
        return repository.findById(id, detail)
                .orElseThrow(() -> new NoSuchElementException("Uživatel nenalezen."));
    }

    public User update(User user) {
        repository.update(user);
        return user;
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    private Set<String> getValidPersonIds() throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get("dataPersonId.txt"))) {
            return lines.collect(Collectors.toSet());
        }
    }

}
