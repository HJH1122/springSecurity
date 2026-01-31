package com.hjh.practice.member.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hjh.practice.member.dto.MemberDto;
import com.hjh.practice.member.entity.Member;
import com.hjh.practice.member.repository.MemberRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void create(MemberDto memberDto) {
        Member member = Member.builder()
                    .username(memberDto.getUsername())
                    .password(passwordEncoder.encode(memberDto.getPassword1()))
                    .email(memberDto.getEmail())
                    .gender(memberDto.getGender())
                    .department(memberDto.getDepartment())
                    .registration(memberDto.getRegistration())
                    .build();

        memberRepository.save(member);
        
    }
    
}
