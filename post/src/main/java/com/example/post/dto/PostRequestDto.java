package com.example.post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PostRequestDto {
    private String account;
    private String email;
    private String address;
    private String password;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("OTP")
    private String OTP;
}
