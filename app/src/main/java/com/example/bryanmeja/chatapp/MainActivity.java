package com.example.bryanmeja.chatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bryanmeja.chatapp.clasesJSON.Token;
import com.example.bryanmeja.chatapp.services.API;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView btnOk, btnRegister;
    EditText etEmail, etPassword;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOk = (TextView) findViewById(R.id.tvLogin);
        btnRegister = (TextView) findViewById(R.id.tvRegister);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.21:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final API api = retrofit.create(API.class);

        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                email = etEmail.getText().toString();
                password = etPassword.getText().toString();

                api.login(email, password).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(MainActivity.this, "Exito", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });


            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
