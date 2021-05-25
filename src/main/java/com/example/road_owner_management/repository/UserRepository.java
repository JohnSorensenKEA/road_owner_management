package com.example.road_owner_management.repository;

import com.example.road_owner_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
