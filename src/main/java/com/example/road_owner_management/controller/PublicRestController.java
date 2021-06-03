package com.example.road_owner_management.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicRestController {

    @GetMapping("/loggedIn")
    public boolean loggedIn(Authentication authentication){
        return authentication != null;
    }
}
