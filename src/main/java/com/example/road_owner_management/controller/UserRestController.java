package com.example.road_owner_management.controller;

import com.example.road_owner_management.model.User;
import com.example.road_owner_management.repository.UserRepository;
import com.example.road_owner_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/user/")
public class UserRestController {

    @Autowired
    UserService userService;

    @GetMapping("/allAvailableUsers")
    public List<User> allAvailableUsers(){
        return userService.getUnassignedUsers();
    }
}
