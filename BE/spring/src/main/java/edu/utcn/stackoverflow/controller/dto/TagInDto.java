package edu.utcn.stackoverflow.controller.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class TagInDto {
    @NotNull
    @Length(min = 1, max = 20)
    private String name;
}
