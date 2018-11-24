package com.example.bryanmeja.chatapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.bryanmeja.chatapp.Adaptadores.BubblesInflater;
import com.example.bryanmeja.chatapp.Compresion.Compresion;
import com.example.bryanmeja.chatapp.Compresion.ConvertBytes;
import com.example.bryanmeja.chatapp.clasesJSON.user;
import com.example.bryanmeja.chatapp.clasesJSON.messages;
import com.example.bryanmeja.chatapp.services.API;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IndividualChat extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_STORAGE = 1000;
    private static final int READ_REQUEST_CODE = 42;
    Compresion compressionLZW = new Compresion();
    EditText textoMensaje;
    ImageView btnEnviar, btnOpciones, btnUpdate;
    List<messages> listaMensajes;
    ListView LvMensajes;
    String mainData,path, base64Decode, fileName, compressedFile;
    File decompressionFile;
    byte[] bytesFromFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_chat);
        LvMensajes = findViewById(R.id.lv_messages);
        btnOpciones = findViewById(R.id.btnOptions);
        btnUpdate = findViewById(R.id.btnUpdate);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST_STORAGE);
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_STORAGE);
        }

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        final Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://192.168.0.21:3000")
                .addConverterFactory(GsonConverterFactory.create()).build();

        final API api = retrofit.create(API.class);

        api.receiveMessages(MainActivity.TokenString).enqueue(new Callback<List<messages>>() {
            @Override
            public void onResponse(Call<List<messages>> call, Response<List<messages>> response) {
                listaMensajes = response.body();


                //intanciar mi BaseAdapter de chats
                LvMensajes.setAdapter(new BubblesInflater(IndividualChat.this, listaMensajes,MainActivity.usuarioActual));
            }
            @Override
            public void onFailure(Call<List<messages>> call, Throwable t) {
                Toast.makeText(IndividualChat.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(IndividualChat.this, MainActivity.class);
                startActivity(intent);
            }
        });

        textoMensaje = (EditText) findViewById(R.id.editTextEnviar);
        btnEnviar = (ImageView) findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                messages message = new messages(
                        MainActivity.usuarioActual.username,
                        MainActivity.usuarioReceptor,
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

        btnOpciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(IndividualChat.this, btnOpciones);
                 popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getTitle().equals("Imágenes")) {

                            Toast.makeText(IndividualChat.this, "Vamos a mandar imágenes", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        else
                        if(menuItem.getTitle().equals("Texto")) {
                            fileSearch();
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(IndividualChat.this, "Chat actualizado", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void fileSearch() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        startActivityForResult(intent,READ_REQUEST_CODE);
    }

    private String readText(String input) {
        File file = new File(Environment.getExternalStorageDirectory(),input);
        StringBuilder text  = new StringBuilder();
        fileName = file.getName();
        decompressionFile = file;
        try {

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null) {
                text.append(line);
            }
            br.close();

            bytesFromFile = ConvertBytes.getBytes(file);
            base64Decode = Base64.encodeToString(bytesFromFile, Base64.DEFAULT);
            base64Decode = base64Decode.replace("\n","");
            decompressionFile = file;

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return base64Decode.toString();
    }

    protected  void onActivityResult (int requestCode, int resultCode, Intent data) {
        try {
            if(requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                if(data != null) {
                    Uri uri = data.getData();
                    path = uri.getPath();
                    path = path.substring(path.indexOf(":")+1);
                    mainData = readText(path);mainData = readText(path);
                    compressedFile = compressionLZW.compress(mainData);
                    Toast.makeText(this, "Texto obtenido", Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch(Exception ex) {
            Toast.makeText(this, "Error en la selección del archivo", Toast.LENGTH_SHORT).show();
        }
    }

}
