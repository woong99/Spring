package com.example.validation.dto;

import com.example.validation.annotation.YearMonth;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

@Data
public class User {
    private String name;
    @Max(90)
    private int age;

    @Email
    private String email;

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번호의 양식과 맞지 않습니다.")
    private String phoneNumber;

    @YearMonth()
    private String reqYearMonth;


}
