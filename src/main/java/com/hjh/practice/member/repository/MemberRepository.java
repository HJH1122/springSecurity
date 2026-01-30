package com.hjh.practice.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hjh.practice.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    
    
}
