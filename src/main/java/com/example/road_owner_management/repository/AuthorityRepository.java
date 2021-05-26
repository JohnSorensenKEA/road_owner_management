package com.example.road_owner_management.repository;

import com.example.road_owner_management.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    Optional<Authority> findAuthorityByRole(String role);
}
