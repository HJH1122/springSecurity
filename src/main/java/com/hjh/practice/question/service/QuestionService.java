package com.hjh.practice.question.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hjh.practice.question.dto.QuestionDto;
import com.hjh.practice.question.entity.Question;
import com.hjh.practice.question.repository.QuestionRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getList() {
       
        return questionRepository.findAll();
    }

    public Question getQuestion(Long id) {

        return questionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("해당 질문이 없습니다"));
    }

    public void create(QuestionDto questionDto) {

        Question question = Question.builder()
                .content(questionDto.getContent())
                .subject(questionDto.getSubject())
                .build();

        questionRepository.save(question);
    }
    
}
