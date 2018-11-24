package com.example.bryanmeja.chatapp.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bryanmeja.chatapp.MainActivity;
import com.example.bryanmeja.chatapp.R;
import com.example.bryanmeja.chatapp.clasesJSON.user;
import com.example.bryanmeja.chatapp.clasesJSON.messages;

import java.util.List;

public class BubblesInflater extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context contexto;
    List<messages> mensajes;
    user userLoggeado;

    public BubblesInflater (Context context, List<messages> mensajes, user user){

        this.contexto = context;
        this.mensajes = mensajes;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        userLoggeado = user;
    }
    @Override
    public int getCount() {
        return mensajes.size();
    }

    @Override
    public Object getItem(int position) {
        return mensajes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){

            if (mensajes.get(position).usuario_emisor.compareTo(MainActivity.usuarioReceptor) == 0){
                view = inflater.inflate(R.layout.my_message, null);
                TextView mensaje = view.findViewById(R.id.message_body);
                mensaje.setText(mensajes.get(position).mensaje);
            }else{
                view = inflater.inflate(R.layout.their_message, null);
                TextView mensaje = view.findViewById(R.id.their_body);
                TextView name = view.findViewById(R.id.their_name);
                name.setText(mensajes.get(position).usuario_emisor);
                mensaje.setText(mensajes.get(position).mensaje);
            }

        }
        return view;
    }
}
