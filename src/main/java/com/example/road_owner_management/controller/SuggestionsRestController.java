package com.example.road_owner_management.controller;


import com.example.road_owner_management.model.Suggestion;
import com.example.road_owner_management.repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SuggestionsRestController {

    @Autowired
    SuggestionRepository suggestionRepository;

    @GetMapping("/allSuggestions")
    public List<Suggestion> allSuggestions(){
        return suggestionRepository.findAll();
    }


    @PostMapping(value = "/newSuggestion", consumes = "application/json")
    public ResponseEntity<Suggestion> newSuggestion(@RequestBody Suggestion suggestion){
        Suggestion suggestion1 = new Suggestion(suggestion.getAuthor(), suggestion.getSuggestion());
        Suggestion newSuggestion = suggestionRepository.save(suggestion1);

        return new ResponseEntity<>(newSuggestion, HttpStatus.CREATED);
    }

}
