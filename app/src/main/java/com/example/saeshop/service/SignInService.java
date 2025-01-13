package com.example.saeshop.service;
import com.example.saeshop.dto.SignInRequestDTO;
import com.example.saeshop.dto.SignInResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignInService {
    @POST("signin/validate")
    Call<SignInResponseDTO> login(@Body SignInRequestDTO request);
}
