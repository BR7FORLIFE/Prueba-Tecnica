package com.files.__airplane_agendant.repository;

import org.springframework.stereotype.Repository;

import com.files.__airplane_agendant.models.User;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}
