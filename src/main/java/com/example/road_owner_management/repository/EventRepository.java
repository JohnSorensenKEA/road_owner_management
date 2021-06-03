package com.example.road_owner_management.repository;

import com.example.road_owner_management.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query("SELECT e FROM events e WHERE e.startTime > CURRENT_TIMESTAMP OR e.endTime > CURRENT_TIMESTAMP ORDER BY e.startTime")
    List<Event> getComingEvents();
}
