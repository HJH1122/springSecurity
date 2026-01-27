package com.hjh.practice.question.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hjh.practice.question.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    
    Page<Question> findAll(Pageable pageable);
    
}
