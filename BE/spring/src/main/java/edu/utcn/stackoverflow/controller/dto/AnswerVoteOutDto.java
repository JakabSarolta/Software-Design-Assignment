package edu.utcn.stackoverflow.controller.dto;

import lombok.Data;

@Data
public class AnswerVoteOutDto {
    private Long id;
    private UserOutDto author;
    private String voteType;
}
