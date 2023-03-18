package edu.utcn.stackoverflow.controller.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AnswerOutDto {
    private Long id;
    private UserOutDto author;
    private String content;
    private String picture;
    private Timestamp date;
    //private QuestionOutDto question;
}
