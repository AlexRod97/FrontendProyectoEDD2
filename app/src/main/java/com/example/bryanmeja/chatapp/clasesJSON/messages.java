package com.example.bryanmeja.chatapp.clasesJSON;

import com.google.gson.annotations.SerializedName;

public class messages {

    @SerializedName("usuario_emisor")
    public String usuario_emisor;
    @SerializedName("usuario_receptor")
    public String usuario_receptor;
    @SerializedName("mensaje")
    public String mensaje;
    @SerializedName("esArchivo")
    public boolean esArchivo;
    @SerializedName("nombreArchivo")
    public String nombreArchivo;

    public messages(String usuario_emisor, String usuario_receptor, String mensaje, boolean esArchivo, String nombreArchivo){

        this.usuario_emisor = usuario_emisor;
        this.usuario_receptor = usuario_receptor;
        this.mensaje = mensaje;
        this.esArchivo = esArchivo;
        this.nombreArchivo = nombreArchivo;
    }
}
