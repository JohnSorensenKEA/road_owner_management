package com.example.road_owner_management.service;

import com.example.road_owner_management.model.User;

import java.util.List;

public interface UserService {

    List<User> getUnassignedUsers();

    List<User> getAdmins();

    User createUser(User user);

    User updateUserEmail(User user);

    User updateUserPassword(User user);

    User updateUserAdmin(User user);

    boolean deleteUser(User user);

}
