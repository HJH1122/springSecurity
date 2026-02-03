package com.hjh.practice.question.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hjh.practice.answer.dto.AnswerDto;
import com.hjh.practice.member.entity.Member;
import com.hjh.practice.member.service.MemberService;
import com.hjh.practice.question.dto.QuestionDto;
import com.hjh.practice.question.entity.Question;
import com.hjh.practice.question.repository.QuestionRepository;
import com.hjh.practice.question.service.QuestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/question")
@Slf4j
public class QuestionController {

    private final QuestionService questionService;
    private final MemberService memberService;


    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Question> paging = questionService.getList(page);

        model.addAttribute("paging", paging);

        return "question/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, AnswerDto answerDto) {

        log.info("id" + id);
        Question question = questionService.getQuestion(id);

        model.addAttribute("question", question);
        model.addAttribute("answerDto", answerDto);
        return "question/detail";
    }

    @GetMapping("/create")
    public String createQuestion(QuestionDto questionDto, Model model) {

        model.addAttribute("questionDto", questionDto);

        return "question/inputForm";
    }

    @PostMapping("/create")
    public String create(@Valid QuestionDto questionDto, BindingResult bindingResult, Principal principal) {

        if(bindingResult.hasErrors()){
            return "question/inputForm";
        }
        Member member = memberService.getMember(principal.getName());

        questionService.create(questionDto, member);

        return "redirect:/question/list";
    }
}
