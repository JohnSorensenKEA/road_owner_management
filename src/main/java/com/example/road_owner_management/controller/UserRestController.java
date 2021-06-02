package com.example.road_owner_management.controller;

import com.example.road_owner_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    UserRepository userRepository;


}
