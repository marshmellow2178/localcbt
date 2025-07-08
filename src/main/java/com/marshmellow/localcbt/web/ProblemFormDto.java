package com.marshmellow.localcbt.web;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProblemFormDto {
    private Long id;

    @NotBlank(message = "문제를 입력해주세요")
    private String question;

    @NotBlank(message = "정답을 입력해주세요")
    private String answer;

}
