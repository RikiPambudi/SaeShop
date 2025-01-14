package com.example.saeshop.adapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ProductListAdapter {
    private static final String BASE_URL = "https://saeshop-api.vercel.app/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
