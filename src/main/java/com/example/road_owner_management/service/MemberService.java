package com.example.road_owner_management.service;

import com.example.road_owner_management.model.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    List<Member> getAllMembers();

    Optional<Member> getMemberById(Integer id);

    Member newMember(Member member);

    Member updateMember(Member member);

    void deleteMember(Member member);
}
