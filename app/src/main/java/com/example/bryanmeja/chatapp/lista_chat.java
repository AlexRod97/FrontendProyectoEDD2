package com.example.bryanmeja.chatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bryanmeja.chatapp.Adaptadores.ListaChatAdaptador;
import com.example.bryanmeja.chatapp.clasesJSON.messages;
import com.example.bryanmeja.chatapp.clasesJSON.user;
import com.example.bryanmeja.chatapp.services.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class lista_chat extends AppCompatActivity {

    ListView lista_chats;
    List<String> usuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_chat);

        lista_chats = findViewById(R.id.listViewChat);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.21:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        api.obtainUser(MainActivity.TokenString).enqueue(new Callback<List<user>>() {
            @Override
            public void onResponse(Call<List<user>> call, Response<List<user>> response) {

                if (response.isSuccessful()){

                    for (int i = 0; i < response.body().size(); i++){

                        if (response.body().get(i).nombre.equals(MainActivity.usuarioActual.nombre)){
                            continue;
                        }else{
                            usuarios.add(response.body().get(i).nombre);
                        }

                        lista_chats.setAdapter(new ListaChatAdaptador(lista_chat.this, usuarios));

                    }
                }
                else {

                    Toast.makeText(lista_chat.this, "JWT invalido, regresando login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(lista_chat.this, MainActivity.class);
                    startActivity(intent);
                }


            }

            @Override
            public void onFailure(Call<List<user>> call, Throwable t) {

                Toast.makeText(lista_chat.this, "JWT invalido, regresando login", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(lista_chat.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
}
