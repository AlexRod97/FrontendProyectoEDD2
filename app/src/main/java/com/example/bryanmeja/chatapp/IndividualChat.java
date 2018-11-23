package com.example.bryanmeja.chatapp;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bryanmeja.chatapp.clasesJSON.messages;
import com.example.bryanmeja.chatapp.services.API;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IndividualChat extends AppCompatActivity {

    EditText textoMensaje;
    ImageView btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_chat);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.7:3000")
                .addConverterFactory(GsonConverterFactory.create()).build();




        textoMensaje = (EditText) findViewById(R.id.editTextEnviar);
        btnEnviar = (ImageView) findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                messages message = new messages(
                        "Bryan0xFF",
                        "AlexRod97",
                        textoMensaje.getText().toString(),
                        false, "mensaje"
                );

                API api = retrofit.create(API.class);

                Call<messages> call = api.sendMessage(message);
                call.enqueue(new Callback<messages>() {
                    @Override
                    public void onResponse(Call<messages> call, Response<messages> response) {
                        Toast.makeText(IndividualChat.this, "Mensaje enviado", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<messages> call, Throwable t) {
                        Toast.makeText(IndividualChat.this, "error", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }
}
