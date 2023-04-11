package edu.utcn.stackoverflow.controller.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class AnswerInDto {
    @NotNull
    @Length(min = 1, max = 20)
    private String author; //username
    @NotNull
    @Length(min = 1, max = 1000)
    private String content;
    @NotNull
    private String picture;
    @NotNull
    private Timestamp date;
    @NotNull
    private Long question;
}
