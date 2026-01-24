package com.hjh.practice.question.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionDto {

    private String subject;
    private String content;
    
}
