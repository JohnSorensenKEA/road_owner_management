package com.example.road_owner_management.service;

import com.example.road_owner_management.model.Event;

import java.util.List;

public interface EventService {

    List<Event> getComingEvents();

    Event newEvent(Event event);

    void deleteEvent(Event event);

    List<Event> getAllEvents();
}
