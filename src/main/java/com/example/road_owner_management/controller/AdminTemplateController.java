package com.example.road_owner_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class AdminTemplateController {

    @GetMapping("/medlemmer")
    public String members(){
        return "admin/member-overview";
    }

    @GetMapping("/users")
    public String users(){
        return "admin/user-overview";
    }

}
