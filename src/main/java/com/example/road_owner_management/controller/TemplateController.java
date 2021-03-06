package com.example.road_owner_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    @GetMapping("/")
    public String index(){
        return "public/indexR";
    }

    @GetMapping("/om")
    public String about(){
        return "public/about";
    }

    @GetMapping("/i")
    public String i(){return "public/indexR";}

    @GetMapping("/admin/events")
    public String adminEvents(){
        return "admin/event";
    }
}
