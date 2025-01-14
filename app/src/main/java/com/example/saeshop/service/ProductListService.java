package com.example.saeshop.service;

import com.example.saeshop.dto.ProductDTO;
import com.example.saeshop.dto.UserDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
public interface ProductListService {
    @GET("product/list")
    Call<List<ProductDTO>> getProductList();
}
