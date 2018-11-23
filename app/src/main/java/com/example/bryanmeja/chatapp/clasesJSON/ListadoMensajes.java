package com.example.bryanmeja.chatapp.clasesJSON;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListadoMensajes {

    public List<messages> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<messages> mensajes) {
        this.mensajes = mensajes;
    }

    @SerializedName("messageArray")
    private List<messages> mensajes;


}
