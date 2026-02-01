package com.hjh.practice.member.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hjh.practice.member.entity.Member;
import com.hjh.practice.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberSecurityService implements UserDetailsService{

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> loginMember = memberRepository.findByUsername(username);

        if(loginMember.isEmpty()){
            throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다." + username);
        }

        Member member = loginMember.get();

        return null;
    }
    
}
