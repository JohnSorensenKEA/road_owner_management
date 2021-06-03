package com.example.road_owner_management.service;

import com.example.road_owner_management.model.Member;
import com.example.road_owner_management.model.User;
import com.example.road_owner_management.repository.MemberRepository;
import com.example.road_owner_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    UserRepository userRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(Integer id) {
        return memberRepository.findById(id);
    }

    public Member newMember(Member member) {
        Member realMember = new Member(member.getRoadName(), member.getRoadNumber(), member.getMemberNumber(), member.getOwnerName());
        return memberRepository.save(realMember);
    }

    public Member updateMember(Member member){
        Optional<Member> optMember = memberRepository.findById(member.getId());
        if (optMember.isPresent()){
            Member realMember = optMember.get();
            realMember.setMemberNumber(member.getMemberNumber());
            realMember.setRoadName(member.getRoadName());
            realMember.setRoadNumber(member.getRoadNumber());
            realMember.setOwnerName(member.getOwnerName());

            if (member.getUser() != null && realMember.getUser() == null){
                Optional<User> optUser = userRepository.findById(member.getUser().getId());

                if (optUser.isPresent()){
                    User user = optUser.get();
                    realMember.setUser(user);
                }
            }
            memberRepository.save(realMember);
            return realMember;
        }

        return member;
    }

    public void deleteMember(Member member){
        Optional<Member> optMember = memberRepository.findById(member.getId());
        if (optMember.isPresent()){
            memberRepository.delete(optMember.get());
        }
    }

    public Member removeMemberUser(Member member) {
        Optional<Member> optMember = memberRepository.findById(member.getId());
        if (optMember.isPresent()){
            Member realMember = optMember.get();
            realMember.setUser(null);
            memberRepository.save(realMember);

            return realMember;
        } else {
            return member;
        }
    }
}
