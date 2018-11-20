package com.example.bryanmeja.chatapp.clasesJSON;

public class messages {

    String usuario_emisor;
    String usuario_receptor;
    String mensaje;
    boolean esArchivo;
    String nombreArchivo;

    public messages(String usuario_emisor, String usuario_receptor, String mensaje, boolean esArchivo, String nombreArchivo){

        this.usuario_emisor = usuario_emisor;
        this.usuario_receptor = usuario_receptor;
        this.mensaje = mensaje;
        this.esArchivo = esArchivo;
        this.nombreArchivo = nombreArchivo;

    }
}
