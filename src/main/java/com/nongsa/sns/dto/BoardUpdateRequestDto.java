package com.nongsa.sns.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class BoardUpdateRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
