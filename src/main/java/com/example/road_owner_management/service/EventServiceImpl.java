package com.example.road_owner_management.service;

import com.example.road_owner_management.model.Event;
import com.example.road_owner_management.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class EventServiceImpl implements EventService{

    @Autowired
    EventRepository eventRepository;

    @Override
    public List<Event> getComingEvents() {
        return eventRepository.getComingEvents();
    }

    @Override
    public Event newEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }

    @Override
    public List<Event> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        Collections.reverse(events);
        return events;
    }
}
