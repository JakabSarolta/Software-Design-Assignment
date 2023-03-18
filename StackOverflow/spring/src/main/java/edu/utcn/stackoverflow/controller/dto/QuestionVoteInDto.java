package edu.utcn.stackoverflow.controller.dto;

import lombok.Data;

@Data
public class QuestionVoteInDto {
    private String author;
    private Long question;
    private String voteType;
}
