package com.example.saeshop.service;

import com.example.saeshop.dto.ProductDetailDTO;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductDetailService {
    @GET("product")
    Call<ProductDetailDTO> getProductDetail();
}
