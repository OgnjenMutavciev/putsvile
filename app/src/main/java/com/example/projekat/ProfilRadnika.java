package com.example.projekat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ProfilRadnika extends AppCompatActivity {

    protected static final String URLserver = "http://mx.lavelektronik.com:3000";
    protected static final HttpHelper httpHelper = new HttpHelper();
    protected String naziv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_radnika);
        naziv = getIntent().getStringExtra("naziv");
        ucitaj();


    }
    protected void ucitaj() {
        new Thread(() -> {
            String URL = URLserver + "/api/radnici/" + naziv;
            try {
                JSONObject radnikapi = httpHelper.getJSONObjectFromURL(URL);
                if(!radnikapi.getBoolean("uspeh")) {
                    Toast.makeText(getApplicationContext(),
                                    "Greska!",
                                    Toast.LENGTH_LONG)
                            .show();
                } else {
                    JSONObject radnik = radnikapi.getJSONObject("radnik");
                    String nazivS = radnik.getString("naziv");
                    String ocenaS = radnik.getString("ocena");
                    String cenaS = radnik.getString("cena");
                    String radnoIskustvoS = radnik.getString("radnoIskustvo");
                    String dodatneSposobnostiS = radnik.getString("dodatneSposobnosti");
                    String datumRodjenjaS = radnik.getJSONObject("rodjenje").getString("datum");
                    String mestoRodjenjaS = radnik.getJSONObject("rodjenje").getString("mesto");
                    runOnUiThread(()->{
                        TextView naziv = findViewById(R.id.imeiprezime);
                        naziv.setText(nazivS);
                        TextView ocena = findViewById(R.id.ocena);
                        ocena.setText(ocenaS);
                        TextView cena = findViewById(R.id.cena);
                        cena.setText(cenaS);
                        TextView radnoIskustvo = findViewById(R.id.radnoiskustvo);
                        radnoIskustvo.setText(radnoIskustvoS);
                        TextView dodatneSposobnosti = findViewById(R.id.dodatnesposobnosti);
                        dodatneSposobnosti.setText(dodatneSposobnostiS);
                        TextView datumRodjenja = findViewById(R.id.datumrodjenja);
                        datumRodjenja.setText(datumRodjenjaS);
                        TextView mestoRodjenja = findViewById(R.id.mestorodjenja);
                        mestoRodjenja.setText(mestoRodjenjaS);





                    });
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }).start();
    }
}