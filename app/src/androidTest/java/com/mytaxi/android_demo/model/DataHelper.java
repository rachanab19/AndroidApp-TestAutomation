package com.mytaxi.android_demo.model;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This is a helper class to fetch the up-to-date credentials from the given URL
 */
public class DataHelper {

    private JsonObject fetchUpToDateCredentials(){
        String jsonResponse = null;
        JsonObject jsonObject = null;
        try{
            HttpURLConnection urlConnection = null;
            URL url = new URL(Constants.URL);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();

            jsonResponse = sb.toString();
            Log.e("@fetchUpToDateCredentials", "Json response: "+jsonResponse );
            JsonParser parser = new JsonParser();
            jsonObject = parser.parse(jsonResponse).getAsJsonObject();
        }
        catch(Exception ex){
            Log.e("@fetchUpToDateCredentials()", "IN EXCEPTION---> "+ ex.getMessage() );
        }
        return jsonObject;
    }

    protected String [] getUpToDateCredentials(){
        String username = null;
        String password = null;
        String [] upToDateCredentials = new String[2];
        JsonObject jsonObject = fetchUpToDateCredentials();
        JsonArray jsonArray = jsonObject.getAsJsonArray("results");
        for (JsonElement jsonElement :jsonArray) {
            JsonObject jsonUser = jsonElement.getAsJsonObject();
            JsonObject userData = jsonUser.getAsJsonObject("login");
            username = userData.get("username").getAsString();
            password = userData.get("password").getAsString();
        }
        if((password!=null && (!"".equals(password.trim())))
                && (username!=null && (!"".equals(username.trim())))){
            upToDateCredentials[0] = username;
            upToDateCredentials[1] = password;
        }
        else{
            upToDateCredentials = null;
        }

        return upToDateCredentials;
    }



}
