package edu.utcn.stackoverflow.controller.dto;

import lombok.Data;
import lombok.ToString;

@Data
public class UserOutDto {
    private Integer id;
    private String lastName;
    private String firstName;
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    private Integer role;
}
