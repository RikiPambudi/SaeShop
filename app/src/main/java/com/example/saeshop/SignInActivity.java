package com.example.saeshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.material.textfield.TextInputEditText;
import android.widget.Toast;
import android.app.ProgressDialog;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.saeshop.adapter.SignInAdapter;
import com.example.saeshop.dto.SignInRequestDTO;
import com.example.saeshop.dto.SignInResponseDTO;
import com.example.saeshop.service.SignInService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    private TextInputEditText editUsername, editPassword;
    private Button loginButton;
    private ProgressDialog progressDialog;


    Intent intentInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.signin_view);

        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

    }

    private void handleLogin() {
        String username = editUsername.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username dan Password harus diisi ", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.show();
        
        SignInService apiService = SignInAdapter.getClient().create(SignInService.class);
        SignInRequestDTO request = new SignInRequestDTO(username, password);

        Call<SignInResponseDTO> call = apiService.login(request);
        call.enqueue(new Callback<SignInResponseDTO>() {
            @Override
            public void onResponse(Call<SignInResponseDTO> call, Response<SignInResponseDTO> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isSuccess()) {
                        Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                        SignInActivity.this.startActivity(intent);
                    } else {
                        Toast.makeText(SignInActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignInActivity.this, "Login gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignInResponseDTO> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SignInActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
