package com.hjh.practice.answer.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hjh.practice.answer.service.AnswerService;
import com.hjh.practice.question.entity.Question;
import com.hjh.practice.question.service.QuestionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/answer")
@Slf4j
public class AnswerController {
    
    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    public String create(@PathVariable("id") Long id, @RequestParam("content") String content) {
        
        Question question =  questionService.getQuestion(id);
        answerService.create(question, content);
        
        return "redirect:/question/detail/" + id;
    }

}
