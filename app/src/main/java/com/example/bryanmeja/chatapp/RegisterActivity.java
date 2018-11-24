package com.example.bryanmeja.chatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bryanmeja.chatapp.clasesJSON.user;
import com.example.bryanmeja.chatapp.services.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    EditText etName, etEmail, etUserName, etPassword;
    com.example.bryanmeja.chatapp.clasesJSON.user user;
    String name, email, userName, password;
    TextView btnRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegistrar =  findViewById(R.id.tvRegistrar);
        etName = findViewById(R.id.etNombre);
        etEmail = findViewById(R.id.etEmail);
        etUserName = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString();
                email = etEmail.getText().toString();
                userName = etUserName.getText().toString();
                password = etPassword.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.0.21:3000")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                API api = retrofit.create(API.class);

                user = new user(name, email, userName, password);

                api.signup(user).enqueue(new Callback<com.example.bryanmeja.chatapp.clasesJSON.user>() {
                    @Override
                    public void onResponse(Call<com.example.bryanmeja.chatapp.clasesJSON.user> call, Response<com.example.bryanmeja.chatapp.clasesJSON.user> response) {
                        Toast.makeText(RegisterActivity.this, "Sended", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<com.example.bryanmeja.chatapp.clasesJSON.user> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });

    }



}
