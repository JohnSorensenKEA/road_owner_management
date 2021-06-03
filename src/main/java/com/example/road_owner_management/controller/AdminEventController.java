package com.example.road_owner_management.controller;

import com.example.road_owner_management.model.Event;
import com.example.road_owner_management.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/event/")
public class AdminEventController {

    @Autowired
    EventService eventService;

    @GetMapping("/allComingEvents")
    public List<Event> getAllComingEvents(){
        return eventService.getComingEvents();
    }

    @GetMapping("/all")
    public List<Event> getAll(){
        return eventService.getAllEvents();
    }

    @PostMapping(value = "/newEvent", consumes = "application/json")
    public ResponseEntity<Event> newEvent(@RequestBody Event event){
        Event newEvent = eventService.newEvent(event);
        return new ResponseEntity<>(newEvent, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteEvent", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEvent(@RequestBody Event event){
        eventService.deleteEvent(event);
    }
}
