package com.example.saeshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.ProgressDialog;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.saeshop.adapter.UserAdapter;
import com.example.saeshop.dto.UserDTO;
import com.example.saeshop.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private Button logoutBtn;
    private ProgressDialog progressDialog;

    private TextView profileName, profileInfoContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.profile_view);

        logoutBtn = findViewById(R.id.logoutBtn);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogout();
            }
        });

        profileName = findViewById(R.id.profileName);
        profileInfoContact = findViewById(R.id.profileInfoContact);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        getUserInfo();
    }

    private void handleLogout() {
        Intent intent = new Intent(ProfileActivity.this, SignInActivity.class);
        ProfileActivity.this.startActivity(intent);
    }

    private void getUserInfo(){
        UserService userService = UserAdapter.getRetrofitInstance().create(UserService.class);
        Call<UserDTO> call = userService.getUser();
        progressDialog.show();

        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    UserDTO user = response.body();
                    Log.d("MainActivity", "Name: " + user.getName());
                    Log.d("MainActivity", "Email: " + user.getEmail());
                    profileName.setText(user.getName());
                    profileInfoContact.setText(user.getPhone()+ " - " + user.getEmail());
                } else {
                    Log.e("MainActivity", "Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("MainActivity", "Failure: " + t.getMessage());
            }
        });
    }
}
