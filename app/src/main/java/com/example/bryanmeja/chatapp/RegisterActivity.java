package com.example.bryanmeja.chatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.example.bryanmeja.chatapp.clasesJSON.Usuario;

public class RegisterActivity extends AppCompatActivity {
   TextView btnRegistrar, etID, etName, etEmail, etUserName, etPassword;
    Usuario user;
    String id, name, email, userName, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegistrar = findViewById(R.id.tvRegistrar);
        etID = findViewById(R.id.etID);
        etName = findViewById(R.id.name);
        etEmail = findViewById(R.id.etEmail);
        etUserName = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = etID.getText().toString();
                name = etName.getText().toString();
                email = etEmail.getText().toString();
                userName = etUserName.getText().toString();
                password = etPassword.getText().toString();

                user = new Usuario(id, name, email, userName, password);
            }
        });
    }


}
