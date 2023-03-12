package edu.utcn.stackoverflow.controller.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Collection;

@Data
public class QuestionOutDto {
    private Long id;
    private String title;
    private String author;
    private String content;
    private Timestamp date;
    private String picture;
    private Collection<TagOutDto> tags;
}
