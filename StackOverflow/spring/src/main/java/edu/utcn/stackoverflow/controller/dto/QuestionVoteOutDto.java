package edu.utcn.stackoverflow.controller.dto;

import lombok.Data;

@Data
public class QuestionVoteOutDto {
    private Long id;
    private UserOutDto author;
    private String voteType;
}
