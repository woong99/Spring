package com.example.response.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class User {
    private String name;
    private int age;
    private String phoneNumber;
    private String address;
}
