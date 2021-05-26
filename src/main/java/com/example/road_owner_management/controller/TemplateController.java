package com.example.road_owner_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/om")
    public String about(){
        return "about";
    }

    @GetMapping("/dashboard") //TODO: Find a better danish word
    public String dashboard(){
        return "dashboard";
    }
}
