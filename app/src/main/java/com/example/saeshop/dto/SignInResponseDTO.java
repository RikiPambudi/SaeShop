package com.example.saeshop.dto;

public class SignInResponseDTO {
    private boolean success;
    private String message;
    private Integer code;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Integer getToken() {
        return code;
    }
}
