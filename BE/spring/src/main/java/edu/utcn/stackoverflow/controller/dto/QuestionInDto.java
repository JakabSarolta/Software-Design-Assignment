package edu.utcn.stackoverflow.controller.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Collection;

@Data
public class QuestionInDto {
    @NotNull
    @Length(min = 1, max = 100)
    private String title;
    @NotNull
    @Length(min = 1, max = 20)
    private String author; //username
    @NotNull
    @Length(min = 1, max = 1000)
    private String content;
    @NotNull
    private Timestamp date;
    @NotNull
    private String picture;
    private Collection<String> tags; //tag names
}
