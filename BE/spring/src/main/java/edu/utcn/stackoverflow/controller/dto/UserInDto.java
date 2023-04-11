package edu.utcn.stackoverflow.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
public class UserInDto {
    @NotNull
    @Length(min = 1, max = 20)
    @Pattern(regexp = "^[A-Z][a-z]+")
    private String lastName;
    @NotNull
    @Length(min = 1, max = 20)
    @Pattern(regexp = "^[A-Z][a-z]+")
    private String firstName;
    @NotNull
    @Length(min = 1, max = 20)
    private String userName;
    @NotNull
    private String password;
    @NotNull
    @Length(min = 5, max = 30)
    @Pattern(regexp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+\\.[a-z]+$")
    private String email;
    @NotNull
    @Pattern(regexp = "^07[0-9]{8}$")
    private String phoneNumber;
    @NotNull
    private Integer role;
}
