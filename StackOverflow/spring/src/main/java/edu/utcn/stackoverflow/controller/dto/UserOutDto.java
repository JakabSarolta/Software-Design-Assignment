package edu.utcn.stackoverflow.controller.dto;

import lombok.Data;

@Data
public class UserOutDto {
    private Long id;
    private String lastName;
    private String firstName;
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    private Integer role;
}
