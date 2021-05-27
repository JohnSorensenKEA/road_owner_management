package com.example.road_owner_management.controller;

import com.example.road_owner_management.model.Member;
import com.example.road_owner_management.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/member/")
public class AdminMemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/allMembers")
    public List<Member> getAllMembers(){
        return memberService.getAllMembers();
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<Member> getById(@PathVariable Integer id){
        Optional<Member> member = memberService.getMemberById(id);

        if (member.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(member.get(), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/newMember", consumes = "application/json")
    public ResponseEntity<Member> newMember(@RequestBody Member member){
        Member newMember = memberService.newMember(member);
        return new ResponseEntity<>(newMember, HttpStatus.CREATED);
    }

    @PutMapping(value = "/updateMember", consumes = "application/json")
    public ResponseEntity<Member> updateMember(@RequestBody Member member){
        Member updatedMember = memberService.updateMember(member);
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }
}
