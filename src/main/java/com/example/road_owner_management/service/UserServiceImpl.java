package com.example.road_owner_management.service;

import com.example.road_owner_management.model.Authority;
import com.example.road_owner_management.model.User;
import com.example.road_owner_management.repository.AuthorityRepository;
import com.example.road_owner_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public List<User> getUnassignedUsers() {
        List<User> users = new ArrayList<>();

        for (User u : getNonAdmins()){
            if (u.getMember() == null){
                users.add(u);
            }
        }
        return users;
    }
    
    public List<User> getNonAdmins(){
        List<User> users = new ArrayList<>();

        for (User u : userRepository.findAll()){
            for (Authority a : u.getAuthorities()){
                if (a.getRole().equals("USER")){
                    users.add(u);
                }
            }
        }
        return users;
    }

    @Override
    public List<User> getAdmins() {
        List<User> users = new ArrayList<>();

        for (User u : userRepository.findAll()){
            for (Authority a : u.getAuthorities()){
                if (a.getRole().equals("ADMIN")){
                    users.add(u);
                }
            }
        }
        return users;
    }

    @Override
    public User createUser(User user) {
        user.setId(0);
        User newUser = userRepository.save(user);

        Authority authority = authorityRepository.getOne(2); //2 = USER
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        newUser.setAuthorities(authorities);

        return userRepository.save(newUser);
    }

    @Override
    public User updateUserEmail(User user) {
        return null;
    }

    @Override
    public User updateUserPassword(User user) {
        return null;
    }

    @Override
    public User updateUserAdmin(User user) {
        if (userRepository.existsById(user.getId())){
            return userRepository.save(user);
        }

        return user;
    }

    @Override
    public boolean deleteUser(User user) {
        userRepository.delete(user);
        return !userRepository.existsById(user.getId());
    }
}
