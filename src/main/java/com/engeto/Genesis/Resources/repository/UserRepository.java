package com.engeto.Genesis.Resources.repository;

import com.engeto.Genesis.Resources.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
