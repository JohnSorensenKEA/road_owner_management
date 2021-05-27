package com.example.road_owner_management.repository;

import com.example.road_owner_management.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
