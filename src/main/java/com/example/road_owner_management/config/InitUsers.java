package com.example.road_owner_management.config;

import com.example.road_owner_management.model.*;
import com.example.road_owner_management.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitUsers implements CommandLineRunner {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    SuggestionRepository suggestionRepository;

    @Autowired
    EventRepository eventRepository;

    @Override
    public void run(String... args) throws Exception {
        authorities();
        users();
        members();
        suggestions();
        events();
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

        User admin1 = new User("Admin", passwordEncoder.encode("root"), "");
        admin1.setAuthorities(adminAuthoritySet);

        userRepository.save(admin1);

        Set<Authority> memberAuthoritySet = new HashSet<>();
        memberAuthoritySet.add(authorityRepository.getOne(2));

        User member1 = new User("goobyplsing@gmail.com", passwordEncoder.encode("b0b"), "12345678");
        member1.setAuthorities(memberAuthoritySet);

        User member2 = new User("paramyr2@hotmail.com", passwordEncoder.encode("g3rt"), "11223344");
        member2.setAuthorities(memberAuthoritySet);

        User member3 = new User("Q", passwordEncoder.encode("Q"), "63513684");
        member3.setAuthorities(memberAuthoritySet);

        userRepository.save(member1);
        userRepository.save(member2);
        userRepository.save(member3);
    }

    public void members(){
        Member member1 = new Member("Abevej", "24A", "1-2", "Peter Hans Jørgensen");
        Member member2 = new Member("Abevej", "24B", "3", "Lars Hans Jørgensen, Morten Per Jørgensen");
        Member member3 = new Member("Børges Alle", "59", "4", "Hans Peter Rasmussen");
        Member member4 = new Member("Børges Alle", "61", "5", "Bob Ibsen");
        Member member5 = new Member("Børges Alle", "63", "6", "Gertrud Morgensen");

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);
        memberRepository.save(member5);

        //Setting user after persisting member, because of proxy error
        member1 = memberRepository.getOne(1);
        member1.setUser(userRepository.getOne(3));
        memberRepository.save(member1);

        member4 = memberRepository.getOne(4);
        member4.setUser(userRepository.getOne(2));
        memberRepository.save(member4);
    }

    public void suggestions(){
        Suggestion sug = new Suggestion("Emil Sorensen", "JEG VIL HAVE NUTELLA TIL ALLE GENERALFORSAMLINGER");

        suggestionRepository.save(sug);
    }

    public void events(){
        Event event1 = new Event("Taco Tuesday", "Tacos on a Tuesday", LocalDateTime.of(2021,6,8,13,0,0), LocalDateTime.of(2021,6,8,14,0,0));
        Event event2 = new Event("Taco Tuesday", "Tacos on a Tuesday", LocalDateTime.of(2021,6,8,13,0,0), LocalDateTime.of(2021,6,8,14,0,0));
        Event event3 = new Event("Taco Tuesday", "Tacos on a Tuesday", LocalDateTime.of(2021,6,8,13,0,0), LocalDateTime.of(2021,6,8,14,0,0));
        Event event4 = new Event("Taco Tuesday", "Tacos on a Tuesday", LocalDateTime.of(2021,6,8,13,0,0), LocalDateTime.of(2021,6,8,14,0,0));
        Event event5 = new Event("Taco Tuesday", "Tacos on a Tuesday", LocalDateTime.of(2021,6,8,13,0,0), LocalDateTime.of(2021,6,8,14,0,0));
        Event event6 = new Event("Taco Tuesday", "Tacos on a Tuesday", LocalDateTime.of(2021,6,8,13,0,0), LocalDateTime.of(2021,6,8,14,0,0));

        eventRepository.save(event1);
        eventRepository.save(event2);
        eventRepository.save(event3);
        eventRepository.save(event4);
        eventRepository.save(event5);
        eventRepository.save(event6);
    }
}
