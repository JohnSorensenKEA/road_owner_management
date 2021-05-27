package com.example.road_owner_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserTemplateController {

    @GetMapping("/dashboard") //TODO: Find a better danish word
    public String dashboard(){
        return "member/dashboard";
    }
}
