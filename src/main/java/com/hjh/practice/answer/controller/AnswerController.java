package com.hjh.practice.answer.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.hjh.practice.answer.dto.AnswerDto;
import com.hjh.practice.answer.entity.Answer;
import com.hjh.practice.answer.service.AnswerService;
import com.hjh.practice.member.entity.Member;
import com.hjh.practice.member.service.MemberService;
import com.hjh.practice.question.dto.QuestionDto;
import com.hjh.practice.question.entity.Question;
import com.hjh.practice.question.service.QuestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/answer")
@Slf4j
public class AnswerController {
    
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final MemberService memberService;

    @PostMapping("/create/{id}")
    public String create(@PathVariable("id") Long id, @Valid AnswerDto answerDto, BindingResult bindingResult, Model model, Principal principal) {

        Question question =  questionService.getQuestion(id);

        if(bindingResult.hasErrors()){

            model.addAttribute("question", question);
            model.addAttribute("answerDto", answerDto);
            return "question/detail";
        }

        Member member = memberService.getMember(principal.getName());
        
        answerService.create(question, answerDto, member);
        
        return "redirect:/question/detail/" + id;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id") Long id, @ModelAttribute AnswerDto answerDto, Principal principal) {

        Answer answer = answerService.getAnswer(id);

        if(!answer.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }

        answerDto.setContent(answer.getContent());

        return "/answer/inputForm";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String modify(@PathVariable("id") Long id, @Valid AnswerDto answerDto, BindingResult bindingResult ,Principal principal) {

        if(bindingResult.hasErrors()){
            return "/answer/modify";
        }
        Answer answer = answerService.getAnswer(id);

        if(!answer.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }

        answerService.modify(answer, answerDto);

        return "redirect:/question/detail/" + answer.getQuestion().getId();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Principal principal) {

        Answer answer = answerService.getAnswer(id);

        if(!answer.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }
        answerService.delete(answer);

        return "redirect:/question/detail/" + answer.getQuestion().getId();
    }

}
