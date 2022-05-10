package com.example.projekat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class KacenjeOglasa extends AppCompatActivity {

    protected static final String URLserver = "http://mx.lavelektronik.com:3000";
    protected static final HttpHelper httpHelper = new HttpHelper();
    protected UslugaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kacenje_oglasa);



        ListView listview = findViewById(R.id.listview);
        adapter = new UslugaAdapter(this);
        listview.setAdapter(adapter);

        postavi();
    }

    protected void postavi() {
        new Thread(() -> {

            JSONObject json = new JSONObject();


        }).start();
    }
}