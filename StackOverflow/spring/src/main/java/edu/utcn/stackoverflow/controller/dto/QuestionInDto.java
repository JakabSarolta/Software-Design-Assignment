package edu.utcn.stackoverflow.controller.dto;

import edu.utcn.stackoverflow.model.Tag;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Collection;

@Data
public class QuestionInDto {
    private String title;
    private String author;
    private String content;
    private Timestamp date;
    private String picture;
    private Collection<String> tags;
}
