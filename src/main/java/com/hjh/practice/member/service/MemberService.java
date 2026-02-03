package com.hjh.practice.member.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hjh.practice.member.dto.MemberDto;
import com.hjh.practice.member.entity.Member;
import com.hjh.practice.member.repository.MemberRepository;

import jakarta.persistence.EntityNotFoundException;
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

    public Member getMember(String name) {

        Member member = memberRepository.findByUsername(name).orElseThrow(() -> new EntityNotFoundException("해당 사용자가 존재하지 않습니다." + name));

        return member;

    }
    
}
