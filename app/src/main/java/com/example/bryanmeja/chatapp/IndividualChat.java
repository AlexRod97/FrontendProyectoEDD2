package com.example.bryanmeja.chatapp;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bryanmeja.chatapp.Adaptadores.BubblesInflater;
import com.example.bryanmeja.chatapp.clasesJSON.ListadoMensajes;
import com.example.bryanmeja.chatapp.clasesJSON.Usuario;
import com.example.bryanmeja.chatapp.clasesJSON.messages;
import com.example.bryanmeja.chatapp.services.API;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
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
    List<messages> listaMensajes;
    ListView LvMensajes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_chat);
        LvMensajes = findViewById(R.id.lv_messages);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        final Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://10.200.215.34:3000")
                .addConverterFactory(GsonConverterFactory.create()).build();

        final API api = retrofit.create(API.class);

        api.receiveMessages().enqueue(new Callback<List<messages>>() {
            @Override
            public void onResponse(Call<List<messages>> call, Response<List<messages>> response) {
                listaMensajes = response.body();

                Usuario user = new Usuario("Bryan", "bty@test.com", "Bryan0xFF", "abc");
                //intanciar mi BaseAdapter de chats
                LvMensajes.setAdapter(new BubblesInflater(IndividualChat.this, listaMensajes,user));
            }
            @Override
            public void onFailure(Call<List<messages>> call, Throwable t) {
                Toast.makeText(IndividualChat.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        textoMensaje = (EditText) findViewById(R.id.editTextEnviar);
        btnEnviar = (ImageView) findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                messages message = new messages(
                        "AlexRod97",
                        "Bryan0xFF",
                        textoMensaje.getText().toString(),
                        false, "mensaje"
                );
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
