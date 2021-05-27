package com.example.road_owner_management.service;

import com.example.road_owner_management.model.Member;
import com.example.road_owner_management.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(Integer id) {
        return memberRepository.findById(id);
    }

    public Member newMember(Member member) {
        return memberRepository.save(member);
    }

    public Member updateMember(Member member){
        return memberRepository.save(member);
    }
}
