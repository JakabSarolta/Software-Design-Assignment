package edu.utcn.stackoverflow.controller.dto;

import lombok.Data;

@Data
public class QuestionVoteOutDto {
    private Long id;
//    private UserOutDto author;
//    private QuestionOutDto question;
    private String voteType;
}
