package com.hjh.practice.answer.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hjh.practice.answer.dto.AnswerDto;
import com.hjh.practice.answer.service.AnswerService;
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

    @PostMapping("/create/{id}")
    public String create(@PathVariable("id") Long id, @Valid AnswerDto answerDto, BindingResult bindingResult, Model model) {

        Question question =  questionService.getQuestion(id);

        if(bindingResult.hasErrors()){

            model.addAttribute("question", question);
            model.addAttribute("answerDto", answerDto);
            return "question/detail";
        }
        
        answerService.create(question, answerDto);
        
        return "redirect:/question/detail/" + id;
    }

}
