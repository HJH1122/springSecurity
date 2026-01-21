package com.hjh.practice.question.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hjh.practice.question.entity.Question;
import com.hjh.practice.question.repository.QuestionRepository;
import com.hjh.practice.question.service.QuestionService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;


    @GetMapping("/question/list")
    public String list(Model model) {

        List<Question> questionList = questionService.getList();
        model.addAttribute("questionList", questionList);

        return "question/list";
    }
    
    
}
