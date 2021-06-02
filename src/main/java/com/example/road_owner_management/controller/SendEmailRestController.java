package com.example.road_owner_management.controller;

import com.example.road_owner_management.model.EmailModel;
import com.example.road_owner_management.service.SendEmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendEmailRestController {

    @PostMapping(value = "/sendtheemail", consumes = "application/json")
    public ResponseEntity sendEmail(@RequestBody EmailModel email){
        SendEmailService sendEmailService = new SendEmailService(email.getSubject(), email.getMessage());
        sendEmailService.addRecipient(email.getRecipients());

        if(sendEmailService.send()){
            return new ResponseEntity(HttpStatus.OK);
        }
        for(int i = 0; i < email.getRecipients().length; i++){
            System.out.println("Recipients: "+email.getRecipients()[i]);
        }
        System.out.println("Subject: "+email.getSubject());
        System.out.println("Message: "+email.getMessage());
        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }
}
