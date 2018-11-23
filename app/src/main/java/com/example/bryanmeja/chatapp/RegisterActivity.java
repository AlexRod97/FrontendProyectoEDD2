package com.example.bryanmeja.chatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bryanmeja.chatapp.clasesJSON.Usuario;
import com.example.bryanmeja.chatapp.services.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {
   TextView btnRegistrar, etName, etEmail, etUserName, etPassword;
   //cambiar el public static
     Usuario user;
     String name, email, userName, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegistrar =  findViewById(R.id.tvRegistrar);
        etName = findViewById(R.id.etNombre);
        etEmail = findViewById(R.id.etEmail);
        etUserName = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);

        Start();



    }

    public void Start(){

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //id = etID.getText().toString();
                name = etName.getText().toString();
                email = etEmail.getText().toString();
                userName = etUserName.getText().toString();
                password = etPassword.getText().toString();

                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.200.215.34:3000")
                        .build();

                final API api = retrofit.create(API.class);

                user = new Usuario(name, email, userName, password);
                api.signup(user).enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        Toast.makeText(RegisterActivity.this, "Agregado Exitosamente", Toast.LENGTH_LONG).show();
                        Intent a = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(a);
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "Error al agregar", Toast.LENGTH_LONG).show();
                        Intent a = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(a);
                    }
                });




            }
        });

    }


}
