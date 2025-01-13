package com.example.saeshop.dto;

public class SignInResponseDTO {
    private boolean isSuccess;
    private String message;
    private Integer code;

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public Integer getToken() {
        return code;
    }
}
