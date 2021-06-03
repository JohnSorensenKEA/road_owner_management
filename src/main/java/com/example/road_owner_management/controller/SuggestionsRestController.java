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

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
public class SuggestionsRestController {

    @Autowired
    SuggestionRepository suggestionRepository;

    @Autowired
    MemberRepository memberRepository;


    @GetMapping("/allSuggestions")
    public List<Suggestion> allSuggestions(){
        return suggestionRepository.findAll();
    }


    @PostMapping(value = "/newSuggestion", consumes = "application/json")
    public ResponseEntity<Suggestion> newSuggestion(@RequestBody Suggestion suggestion){
        String loggedInName;
        String loggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();

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


    String folderPath="suggestionsDownload/suggestions.txt";

    @GetMapping("/suggestions/download")
    @ResponseBody
    public void downloadSuggestions(HttpServletResponse response) {

        List<Suggestion> suggestionList = suggestionRepository.findAll();
        String suggestions = "";

        try {
            FileWriter fileWriter = new FileWriter(folderPath);
            for (int i = 0; i < suggestionList.size(); i++) {
                suggestions += suggestionList.get(i).toString() + "\n\n";
            }
            fileWriter.write(suggestions);
            fileWriter.close();
        }catch(Exception e){
            System.out.println("Error: " + e);
        }



        response.setHeader("Content-Disposition", "attachment; filename=suggestions");
        response.setHeader("Content-Transfer-Encoding", "binary");
        try {
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            FileInputStream fis = new FileInputStream(folderPath);
            int len;
            byte[] buf = new byte[1024];
            while((len = fis.read(buf)) > 0) {
                bos.write(buf,0,len);
            }
            bos.close();
            response.flushBuffer();
        }
        catch(IOException e) {
            e.printStackTrace();

        }
    }

}
