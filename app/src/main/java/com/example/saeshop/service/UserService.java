package com.example.saeshop.service;

import com.example.saeshop.dto.UserDTO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    @GET("users/riki")
    Call<UserDTO> getUser();
}
