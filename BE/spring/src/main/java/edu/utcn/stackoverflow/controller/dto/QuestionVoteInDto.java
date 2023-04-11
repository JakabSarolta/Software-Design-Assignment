package edu.utcn.stackoverflow.controller.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class QuestionVoteInDto {
    @NotNull
    @Length(min = 1, max = 20)
    private String author;
    @NotNull
    private Long question;
    @NotNull
    private String voteType;
}
