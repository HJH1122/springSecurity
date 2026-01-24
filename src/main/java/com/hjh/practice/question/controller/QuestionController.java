package com.hjh.practice.question.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hjh.practice.question.dto.QuestionDto;
import com.hjh.practice.question.entity.Question;
import com.hjh.practice.question.repository.QuestionRepository;
import com.hjh.practice.question.service.QuestionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/question")
@Slf4j
public class QuestionController {

    private final QuestionService questionService;


    @GetMapping("/list")
    public String list(Model model) {

        List<Question> questionList = questionService.getList();
        model.addAttribute("questionList", questionList);

        return "question/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {

        log.info("id" + id);
        Question question = questionService.getQuestion(id);

        model.addAttribute("question", question);
        return "question/detail";
    }

    @GetMapping("/create")
    public String createQuestion(@PathVariable("id") Long id, Model model) {
        
        return "question/inputForm";
    }

    @PostMapping("/create")
    public String create(QuestionDto questionDto) {
        
        return "redirect:/question/list";
    }
}
