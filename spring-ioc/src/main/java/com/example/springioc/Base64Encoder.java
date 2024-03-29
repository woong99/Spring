package com.example.springioc;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component("base74Encder")
public class Base64Encoder implements IEncoder {
    public String encode(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
