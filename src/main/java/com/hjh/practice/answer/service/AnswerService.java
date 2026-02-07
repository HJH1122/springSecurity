package com.hjh.practice.answer.service;

import org.springframework.stereotype.Service;

import com.hjh.practice.answer.dto.AnswerDto;
import com.hjh.practice.answer.entity.Answer;
import com.hjh.practice.answer.repository.AnswerRepository;
import com.hjh.practice.member.entity.Member;
import com.hjh.practice.question.entity.Question;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
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

    public Answer getAnswer(Long id) {
        Answer answer = answerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 답변을 찾을 수 없습니다."));

        return answer;
    }

    public void modify(Answer answer, @Valid AnswerDto answerDto) {

        answer.setContent(answerDto.getContent());
        answerRepository.save(answer);

    }

    public void delete(Answer answer) {
        
        answerRepository.delete(answer);
    }
    
}
