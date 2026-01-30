package com.hjh.practice.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hjh.practice.member.dto.MemberDto;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    
    @GetMapping("/signup")
    public String signup(Model model) {

        model.addAttribute("memberDto", new MemberDto());
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberDto memberDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            
            return "member/signup";
        }

        return "redirect:/";
    }
    

}
