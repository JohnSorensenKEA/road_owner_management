package com.example.road_owner_management.repository;

import com.example.road_owner_management.model.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestionRepository extends JpaRepository<Suggestion, Integer> {

}
