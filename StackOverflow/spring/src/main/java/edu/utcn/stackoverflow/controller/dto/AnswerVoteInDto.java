package edu.utcn.stackoverflow.controller.dto;

import lombok.Data;

@Data
public class AnswerVoteInDto {
    private String author;
    private Long answer;
    private String voteType;
}
