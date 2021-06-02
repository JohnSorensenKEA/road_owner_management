package com.example.road_owner_management.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SendEmailController {

    @GetMapping("/sendEmail")
    public String sendEmail(){
        return "admin/send-email";
    }
}
