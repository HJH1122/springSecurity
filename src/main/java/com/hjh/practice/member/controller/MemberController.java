package com.hjh.practice.member.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hjh.practice.member.dto.MemberDto;
import com.hjh.practice.member.service.MemberService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    
    @GetMapping("/signup")
    public String signup(Model model) {

        model.addAttribute("memberDto", new MemberDto());
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberDto memberDto, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            
            return "member/signup";
        }

        if(!memberDto.getPassword1().equals(memberDto.getPassword2())){
            bindingResult.rejectValue("password2", "passwordIncorrect", "2개의 패스워드가 일치하지 않습니다.");

            return "member/signup";
        }

        try{
            memberService.create(memberDto);
        } catch(DataIntegrityViolationException e){
            log.info("회원가입 실패: 이미 등록된 사용자");
            model.addAttribute("errorMessage", "이미 등록된 사용자");
            return "member/signup";
        } catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/signup";
        }
        
        

        return "redirect:/";
    }
    

}
