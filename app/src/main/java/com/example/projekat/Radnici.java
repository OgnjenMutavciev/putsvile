package com.example.projekat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Radnici extends AppCompatActivity {

    protected static final String URLserver = "http://mx.lavelektronik.com:3000";
    protected static final HttpHelper httpHelper = new HttpHelper();
    protected RadnikAdapter adapter;
    protected ListView listview;

    protected String tip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radnici);

        listview = findViewById(R.id.listview);
        adapter = new RadnikAdapter(this);
        listview.setAdapter(adapter);
        tip = getIntent().getStringExtra("tip");


        ucitaj();
        itemClick();
    }

    protected void ucitaj() {
        new Thread(() -> {
            String URL = URLserver + "/api/usluge/" + tip;
            try {
                JSONObject uslugeapi = httpHelper.getJSONObjectFromURL(URL);
                if(!uslugeapi.getBoolean("uspeh")) {
                    Toast.makeText(getApplicationContext(),
                                    "Greska!",
                                    Toast.LENGTH_LONG)
                            .show();
                } else {
                    JSONArray sviRadnici = uslugeapi.getJSONObject("usluga").getJSONArray("radnici");
                    for(int i=0;i<sviRadnici.length();i++) {
                        String radnik = sviRadnici.getString(i);
                        String URLradnik = URLserver + "/api/radnici/" + radnik;
                        JSONObject radnikapi = httpHelper.getJSONObjectFromURL(URLradnik).getJSONObject("radnik");
                        String cena = radnikapi.getString("cena");
                        String ocena = radnikapi.getString("ocena");
                        runOnUiThread(() -> {
                            adapter.ubaciElem(radnik, cena, ocena);
                        });
                    }
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }).start();
    }
    protected void itemClick(){
        listview.setOnItemClickListener((parent,view,position,id)->{
            startActivity(new Intent(this, ProfilRadnika.class).putExtra("naziv",adapter.getItemNaziv(position)));
        });
    }
}
