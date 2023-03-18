package edu.utcn.stackoverflow.controller.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AnswerInDto {
    private String author; //username
    private String content;
    private String picture;
    private Timestamp date;
    private Long question;
}
