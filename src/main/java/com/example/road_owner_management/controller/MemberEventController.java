package com.example.road_owner_management.controller;

import com.example.road_owner_management.model.Event;
import com.example.road_owner_management.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/member/event/")
public class MemberEventController {

    @Autowired
    EventService eventService;

    @GetMapping("/comingEvents")
    public List<Event> comingEvents(){
        return eventService.getComingEvents();
    }
}
