package com.example.road_owner_management.config;

import com.example.road_owner_management.model.Authority;
import com.example.road_owner_management.model.Member;
import com.example.road_owner_management.model.User;
import com.example.road_owner_management.repository.AuthorityRepository;
import com.example.road_owner_management.repository.MemberRepository;
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
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MemberRepository memberRepository;

    @Override
    public void run(String... args) throws Exception {
        authorities();
        users();
        members();
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

        User member1 = new User("Bob@mail.dk", passwordEncoder.encode("b0b"), "12345678");
        member1.setAuthorities(memberAuthoritySet);

        User member2 = new User("Gert@mail.dk", passwordEncoder.encode("g3rt"), "11223344");
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
}
