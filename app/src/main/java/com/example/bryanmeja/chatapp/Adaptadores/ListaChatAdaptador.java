package com.example.bryanmeja.chatapp.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bryanmeja.chatapp.IndividualChat;
import com.example.bryanmeja.chatapp.R;

import java.util.List;

public class ListaChatAdaptador extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context contexto;
    List<String> usuariosChat;

    public ListaChatAdaptador(Context context, List<String> usuarios_receptores){

        this.contexto = context;
        usuariosChat = usuarios_receptores;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return usuariosChat.size();
    }

    @Override
    public Object getItem(int position) {
        return usuariosChat.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.elemento_lista_chat, null);
        TextView nombre =  (TextView) vista.findViewById(R.id.tvNombreLista);

        String usuario = usuariosChat.get(position);
        nombre.setText(usuario);

        nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chat = new Intent(contexto, IndividualChat.class);
                contexto.startActivity(chat);
            }
        });

        return vista;
    }
}
