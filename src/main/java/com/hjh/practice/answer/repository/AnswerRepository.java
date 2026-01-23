package com.hjh.practice.answer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hjh.practice.answer.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long>{
    
}
