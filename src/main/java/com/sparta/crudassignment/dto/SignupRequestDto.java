package com.sparta.crudassignment.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Pattern;


@Getter
public class SignupRequestDto {

    @Length(min = 4,max = 10)
    @Pattern(regexp = "^[a-z0-9]*$")
    private String username;

    @Length(min = 8, max=15)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String password;

    private final boolean admin = false;

    private final String adminToken = "";
}