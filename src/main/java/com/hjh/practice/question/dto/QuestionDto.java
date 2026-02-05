package com.hjh.practice.question.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {

    @Size(max=200, message = "제목은 200자 이내로 입력해주세요.")
    @NotEmpty(message = "제목은 필수입니다.")
    private String subject;

    @NotEmpty(message = "내용은 필수입니다.")
    private String content;
    
}
