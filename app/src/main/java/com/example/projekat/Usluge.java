package com.example.projekat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Usluge extends AppCompatActivity {

    protected static final String URLserver = "http://mx.lavelektronik.com:3000";
    protected static final HttpHelper httpHelper = new HttpHelper();
    protected UslugaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usluge);

        ListView listview = findViewById(R.id.listview);
        adapter = new UslugaAdapter(this);
        listview.setAdapter(adapter);

        ucitaj();
    }

    protected void ucitaj() {
        new Thread(() -> {
            String URL = URLserver + "/api/usluge";
            try {
                JSONObject uslugeapi = httpHelper.getJSONObjectFromURL(URL);
                if(!uslugeapi.getBoolean("uspeh")) {
                    Toast.makeText(getApplicationContext(),
                            "Greska!",
                            Toast.LENGTH_LONG)
                            .show();
                } else {
                    JSONArray sveusluge = uslugeapi.getJSONArray("usluge");
                    for(int i=0;i<sveusluge.length();i++) {
                        JSONObject usluga = sveusluge.getJSONObject(i);
                        runOnUiThread(() -> {
                            try {
                                adapter.ubaciElem(usluga.getString("tip"),usluga.getString("kolicina"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }).start();
    }
}