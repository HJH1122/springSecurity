package com.hjh.practice.answer.service;

import org.springframework.stereotype.Service;

import com.hjh.practice.answer.dto.AnswerDto;
import com.hjh.practice.answer.entity.Answer;
import com.hjh.practice.answer.repository.AnswerRepository;
import com.hjh.practice.member.entity.Member;
import com.hjh.practice.question.entity.Question;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void create(Question question, AnswerDto answerDto, Member member) {

        Answer answer = Answer.builder()
            .content(answerDto.getContent())
            .question(question)
            .author(member)
            .build();

        answerRepository.save(answer);
    }
    
}
