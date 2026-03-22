package com.example.quote;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class QuoteApiThread extends Thread{
    MainActivity activity;

    public QuoteApiThread(MainActivity activity) {
        this.activity = activity;
    }
    @Override
    public void run() {
        try {
            //URL connection
            String apiUrl =
                    "https://dummyjson.com/quotes/random";
            URL url = new URL(apiUrl);
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            //reading response
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream)
            );
            StringBuilder response = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                response.append(line);
            }
            reader.close();
            //handle JSON
            JSONObject jsonObject = new JSONObject(response.toString());
            String id = jsonObject.getString("id");
            String quote = jsonObject.getString("quote");
            String author = jsonObject.getString("author");
            String result =
                    "Quote: " + quote +
                            "\n\nAuthor: " + author;

            activity.runOnUiThread(() -> {
                activity.textQuote.setText(result);
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
