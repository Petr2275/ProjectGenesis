package com.engeto.Genesis.Resources.repository;


import com.engeto.Genesis.Resources.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(User user) {
        String sql = "INSERT INTO users (name, surname, person_id, uuid) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getSurname(), user.getPersonId(), user.getUuid());
    }

    public List<User> findAll(boolean detail) {
        String sql = detail ?
                "SELECT * FROM users" :
                "SELECT id, name, surname FROM users";

        return jdbcTemplate.query(sql, new UserRowMapper(detail));
    }

    public Optional<User> findById(Long id, boolean detail) {
        String sql = detail ?
                "SELECT * FROM users WHERE id = ?" :
                "SELECT id, name, surname FROM users WHERE id = ?";

        List<User> users = jdbcTemplate.query(sql, new UserRowMapper(detail), id);
        return users.stream().findFirst();
    }

    public boolean existsByPersonId(String personId) {
        String sql = "SELECT COUNT(*) FROM users WHERE person_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, personId);
        return count != null && count > 0;
    }

    public void update(User user) {
        String sql = "UPDATE users SET name = ?, surname = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getSurname(), user.getId());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
