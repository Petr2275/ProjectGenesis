package com.engeto.Genesis.Resources.service;

import com.engeto.Genesis.Resources.dto.CreateUserRequest;
import com.engeto.Genesis.Resources.model.User;
import com.engeto.Genesis.Resources.repository.UserRepository;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Service
@Data
public class UserService {

    @Autowired
    private UserRepository repository;


    public User create(CreateUserRequest request) throws IOException {
        if (!getValidPersonIds().contains(request.getPersonId())) {
            throw new IllegalArgumentException("Zadaný personId není v seznamu povolených hodnot.");
        }

        // Duplicitní kontrola před uložením
        if (repository.existsByPersonId(request.getPersonId())) {
            throw new IllegalArgumentException("Uživatel s tímto personId již existuje.");
        }

        String uuid = UUID.randomUUID().toString();
        User user = new User(null, request.getName(), request.getSurname(), request.getPersonId(), uuid);
        Long id = repository.save(user);
        user.setId(id);
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
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("dataPersonId.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines().collect(Collectors.toSet());
        }
    }


}
