package com.hjh.practice.answer.service;

import org.springframework.stereotype.Service;

import com.hjh.practice.answer.entity.Answer;
import com.hjh.practice.answer.repository.AnswerRepository;
import com.hjh.practice.question.entity.Question;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void create(Question question, String content) {

        Answer answer = Answer.builder()
            .content(content)
            .question(question)
            .build();

        answerRepository.save(answer);
    }
    
}
