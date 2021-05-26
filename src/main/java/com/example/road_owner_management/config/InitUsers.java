package com.example.road_owner_management.config;

import com.example.road_owner_management.model.Authority;
import com.example.road_owner_management.model.User;
import com.example.road_owner_management.repository.AuthorityRepository;
import com.example.road_owner_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class InitUsers implements CommandLineRunner {

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        authorities();
        users();
    }

    public void authorities(){
        Authority admin = new Authority("ADMIN");
        Authority member = new Authority("USER");

        authorityRepository.save(admin);
        authorityRepository.save(member);
    }

    public void users(){
        Set<Authority> adminAuthoritySet = new HashSet<>();
        adminAuthoritySet.add(authorityRepository.getOne(1));

        User admin1 = new User("Admin", passwordEncoder.encode("root"));
        admin1.setAuthorities(adminAuthoritySet);

        userRepository.save(admin1);

        Set<Authority> memberAuthoritySet = new HashSet<>();
        memberAuthoritySet.add(authorityRepository.getOne(2));

        User member1 = new User("Bob@mail.dk", passwordEncoder.encode("b0b"));
        member1.setAuthorities(memberAuthoritySet);

        User member2 = new User("Gert@mail.dk", passwordEncoder.encode("g3rt"));
        member2.setAuthorities(memberAuthoritySet);

        User member3 = new User("Q", passwordEncoder.encode("Q"));
        member3.setAuthorities(memberAuthoritySet);

        userRepository.save(member1);
        userRepository.save(member2);
        userRepository.save(member3);
    }
}
