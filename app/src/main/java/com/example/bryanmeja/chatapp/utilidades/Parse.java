package com.example.bryanmeja.chatapp.utilidades;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bryanmeja.chatapp.clasesJSON.messages;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Parsea el Usuario, es decir, se llama a /messages/receive, y obtiene todos los mensajes
 */
public class Parse {

    private RequestQueue mQueue;
    private List<messages> dataParsed = new ArrayList<>();
    private String result = "";

    public  List<messages> ParseReceive(String jsonRawData, Context context){

        mQueue = Volley.newRequestQueue(context);
        jsonParse();

        return dataParsed;
    }

    private void jsonParse(){

        String url = "127.0.0.123:3000/messages/receive";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("messagesArray");

                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject message = jsonArray.getJSONObject(i);

                        String usuario_emisor = message.getString("usuario_emisor");
                        String usuario_receptor = message.getString("usuario_receptor");
                        String mensaje = message.getString("mensaje");
                        boolean esArchivo = message.getBoolean("esArchivo");
                        String nombreArchivo = message.getString("nombreArchivo");

                        messages temp = new messages(usuario_emisor,usuario_receptor,mensaje,esArchivo,nombreArchivo);
                        dataParsed.add(temp);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    /**
     * obtiene el token en /login para su posterior uso
     * @param username
     * @param password
     * @return
     */
    public String obtainToken(String username, String password){

        String url = "localhost:3000/login";
        final JSONObject jsonObject = new JSONObject();

        try{

            jsonObject.put("username", username);
            jsonObject.put("password", password);

        }catch (JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    //obtengo el JSON object
                    JSONObject data = response.getJSONObject("token");
                    result = data.toString();

                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                result = error.toString();
            }
        });
        mQueue.add(request);

        return result;
    }
}
