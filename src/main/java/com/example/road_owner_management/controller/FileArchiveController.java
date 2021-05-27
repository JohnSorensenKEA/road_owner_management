package com.example.road_owner_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FileArchiveController {

    @GetMapping("/files")
    public String fileArchive(){
        return "member/filearchive";
    }
}
