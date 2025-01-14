package com.example.saeshop;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saeshop.ProductAdapter;
import com.example.saeshop.dto.ProductDTO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productrycicle_view);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewProducts);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        
        List<ProductDTO> productList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray("[]");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                productList.add(new ProductDTO(
                        jsonObject.getInt("id"),
                        jsonObject.getString("name"),
                        jsonObject.getInt("price"),
                        jsonObject.getString("image")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ProductAdapter adapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(adapter);
    }
}