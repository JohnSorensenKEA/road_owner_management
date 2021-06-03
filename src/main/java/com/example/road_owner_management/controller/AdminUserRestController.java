package com.example.road_owner_management.controller;

import com.example.road_owner_management.model.Member;
import com.example.road_owner_management.model.User;
import com.example.road_owner_management.repository.UserRepository;
import com.example.road_owner_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/user/")
public class AdminUserRestController {

    @Autowired
    UserService userService;

    @GetMapping("/allAvailableUsers")
    public List<User> allAvailableUsers(){
        return userService.getUnassignedUsers();
    }

    @GetMapping("nonAdminUsers")
    public List<User> nonAdminUsers(){
        return userService.getNonAdmins();
    }

    @PostMapping(value = "/newUser", consumes = "application/json")
    public ResponseEntity<User> newMember(@RequestBody User user){
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping(value = "/updateUser", consumes = "application/json")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User newUser = userService.updateUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteUser", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMember(@RequestBody User user){
        userService.deleteUser(user);
    }

    @PutMapping(value = "/resetPassword", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public String resetPassword(@RequestBody User user){
        String newPassword = userService.resetPassword(user);
        return newPassword;
    }
}
