package edu.utcn.stackoverflow.controller.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Collection;

@Data
public class AnswerOutDto {
    private Long id;
    private UserOutDto author;
    private String content;
    private String picture;
    private Timestamp date;
    private Collection<AnswerVoteOutDto> answerVotes;
    //private QuestionOutDto question;
}
