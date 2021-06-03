package com.example.road_owner_management.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SuggestionController {

    @GetMapping("/suggestions")
    public String suggestions(){
        return "member/new-suggestion";
    }
}
