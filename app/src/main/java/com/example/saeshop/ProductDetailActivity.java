package com.example.saeshop;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;

import com.example.saeshop.adapter.ProductDetailAdapter;
import com.example.saeshop.dto.ProductDetailDTO;
import com.example.saeshop.service.ProductDetailService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {
    private TextView priceText, nameText, descriptionText;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.product_detail_view);

        priceText = findViewById(R.id.priceText);
        nameText = findViewById(R.id.nameText);
        descriptionText = findViewById(R.id.descriptionText);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        getProductDetail();
    }

    private void getProductDetail(){
        ProductDetailService productDetailService = ProductDetailAdapter.getRetrofitInstance().create(ProductDetailService.class);
        Call<ProductDetailDTO> call = productDetailService.getProductDetail();
        progressDialog.show();

        call.enqueue(new Callback<ProductDetailDTO>() {
            @Override
            public void onResponse(Call<ProductDetailDTO> call, Response<ProductDetailDTO> response) {
                progressDialog.dismiss();
                    ProductDetailDTO productDetailRes = response.body();
                    Log.d("MainActivity", "des: " + productDetailRes.getDescription());
                    priceText.setText(productDetailRes.getPrice());
                    nameText.setText(productDetailRes.getName());
                    descriptionText.setText(productDetailRes.getDescription());
            }

            @Override
            public void onFailure(Call<ProductDetailDTO> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("MainActivity", "Failure: " + t.getMessage());
            }
        });
    }
}
