package com.hjh.practice.question.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hjh.practice.question.entity.Question;
import com.hjh.practice.question.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getList() {
       
        return questionRepository.findAll();
    }
    
}
