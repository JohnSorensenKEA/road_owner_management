package com.example.road_owner_management.controller;


import com.example.road_owner_management.model.Member;
import com.example.road_owner_management.model.Suggestion;
import com.example.road_owner_management.model.User;
import com.example.road_owner_management.repository.MemberRepository;
import com.example.road_owner_management.repository.SuggestionRepository;
import com.example.road_owner_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SuggestionsRestController {

    @Autowired
    SuggestionRepository suggestionRepository;

    @Autowired
    MemberRepository memberRepository;


    @GetMapping("/allSuggestions")
    public List<Suggestion> allSuggestions(){

//        -------------------------------Find logged in user-----------------------------------
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
//        String loggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();
        String loggedInUser = "paramyr2@hotmail.com";

        List<Member> memberList = memberRepository.findAll();


        for(int i = 0; i<memberList.size(); i++){
            if(memberList.get(i).getUser().getEmail().equals(loggedInUser)){
                System.out.println(memberList.get(i).getOwnerName());
                break;
            }
        }

//      ----------------------------------------------------------------------------------------
        return suggestionRepository.findAll();
    }


    @PostMapping(value = "/newSuggestion", consumes = "application/json")
    public ResponseEntity<Suggestion> newSuggestion(@RequestBody Suggestion suggestion){
        String loggedInName;

        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        String loggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();
//        String loggedInUser = "paramyr2@hotmail.com";

        List<Member> memberList = memberRepository.findAll();


        for(int i = 0; i<memberList.size(); i++){
            if (memberList.get(i).getUser() != null &&
                    memberList.get(i).getUser().getEmail().equals(loggedInUser)) {
                loggedInName = memberList.get(i).getOwnerName();

                System.out.println(loggedInName);

                Suggestion suggestion1 = new Suggestion(loggedInName, suggestion.getSuggestion());
                Suggestion newSuggestion = suggestionRepository.save(suggestion1);
                return new ResponseEntity<>(newSuggestion, HttpStatus.CREATED);
            }
        }
        return null;
    }

    @DeleteMapping("/deleteAllSuggestions")
    public HttpStatus deleteAllSuggestion(){
        suggestionRepository.deleteAll();
        return HttpStatus.OK;
    }

}
