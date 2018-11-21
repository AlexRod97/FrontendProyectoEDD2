package com.example.bryanmeja.chatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.bryanmeja.chatapp.Adaptadores.ListaChatAdaptador;

import java.util.ArrayList;
import java.util.List;

public class lista_chat extends AppCompatActivity {

    ListView lista_chats;
    List<String> usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_chat);

        lista_chats = findViewById(R.id.listViewChat);
        usuarios = new ArrayList<>();
        usuarios.add("Bryan Mejia");
        usuarios.add("Danilo Sandoval");
        usuarios.add("Alex Rodriguez");
        usuarios.add("Pablo Godoy");

        lista_chats.setAdapter(new ListaChatAdaptador(this, usuarios));

    }
}
