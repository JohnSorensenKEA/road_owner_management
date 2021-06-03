package com.example.road_owner_management.service;

import com.example.road_owner_management.model.Authority;
import com.example.road_owner_management.model.User;
import com.example.road_owner_management.repository.AuthorityRepository;
import com.example.road_owner_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public List<User> getUnassignedUsers() {
        List<User> users = new ArrayList<>();

        List<User> nonAdmins = new ArrayList<>();

        for (User u : userRepository.findAll()) {
            for (Authority a : u.getAuthorities()) {
                if (a.getRole().equals("USER")) {
                    users.add(u);
                }
            }
        }

        for (User u : nonAdmins) {
            if (u.getMember() == null) {
                users.add(u);
            }
        }
        return users;
    }

    @Override
    public List<User> getNonAdmins() {
        List<User> users = new ArrayList<>();

        for (User u : userRepository.findAll()) {
            for (Authority a : u.getAuthorities()) {
                if (a.getRole().equals("USER")) {
                    users.add(u);
                }
            }
        }
        return users;
    }

    @Override
    public List<User> getAdmins() {
        List<User> users = new ArrayList<>();

        for (User u : userRepository.findAll()) {
            for (Authority a : u.getAuthorities()) {
                if (a.getRole().equals("ADMIN")) {
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
    public User updateUser(User user) {
        Optional<User> optUser = userRepository.findById(user.getId());
        if (optUser.isPresent()){
            User realUser = optUser.get();
            realUser.setEmail(user.getEmail());
            realUser.setTelephoneNumber(user.getTelephoneNumber());
            userRepository.save(realUser);
            return realUser;
        }
        return user;
    }

    @Override
    public User updateUserPassword(User user) {
        return null;
    }

    @Override
    public User updateUserAdmin(User user) {
        if (userRepository.existsById(user.getId())) {
            return userRepository.save(user);
        }

        return user;
    }

    @Override
    public boolean deleteUser(User user) {
        userRepository.delete(user);
        return !userRepository.existsById(user.getId());
    }

    //Generate, set, return
    @Override
    public String resetPassword(User user) {
        String characters = "0123456789aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
        String generatedCookie = "";
        for(int i = 0; i < 20; i++){
            generatedCookie += characters.charAt(new Random().nextInt(characters.length()));
        }

        Optional<User> optUser = userRepository.findById(user.getId());
        if (optUser.isPresent()){
            User realUser = optUser.get();
            realUser.setPassword(passwordEncoder.encode(generatedCookie));
            userRepository.save(realUser);
        }

        return generatedCookie;
    }
}
