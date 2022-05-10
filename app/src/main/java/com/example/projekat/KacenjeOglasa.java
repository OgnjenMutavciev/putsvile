package com.example.projekat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class KacenjeOglasa extends AppCompatActivity {

    protected static final String URLserver = "http://mx.lavelektronik.com:3000";
    protected static final HttpHelper httpHelper = new HttpHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kacenje_oglasa);
        Button postavioglas = findViewById(R.id.dugmepostavioglas);
        postavioglas.setOnClickListener(view -> postavi());
    }

    protected void postavi() {
        EditText imeprezimeet = findViewById(R.id.imeiprezime);
        String imeprezime = imeprezimeet.getText().toString();
        EditText datumrodjenjaet = findViewById(R.id.datumrodjenja);
        String datumrodjenja = datumrodjenjaet.getText().toString();
        EditText mestorodjenjaet = findViewById(R.id.mestorodjenja);
        String mestorodjenja = mestorodjenjaet.getText().toString();
        EditText ocenaradnesposobnostiet = findViewById(R.id.ocenaradnesposobnosti);
        String ocenaradnesposobnosti = ocenaradnesposobnostiet.getText().toString();
        EditText radnoiskustvoet = findViewById(R.id.radnoiskustvo);
        String radnoiskustvo = radnoiskustvoet.getText().toString();
        EditText dodatnesposobnostiet = findViewById(R.id.dodatnesposobnosti);
        String dodatnesposobnosti = dodatnesposobnostiet.getText().toString();
        EditText cenaet = findViewById(R.id.cena);
        String cena = cenaet.getText().toString();
        JSONObject objekat = new JSONObject();
        JSONObject rodjenje = new JSONObject();
        try {
            objekat.put("naziv", imeprezime);
            rodjenje.put("datum", datumrodjenja);
            rodjenje.put("mesto", mestorodjenja);
            objekat.put("rodjenje", rodjenje);
            objekat.put("ocena", ocenaradnesposobnosti);
            objekat.put("radnoIskustvo", radnoiskustvo);
            objekat.put("dodatneSposobnosti", dodatnesposobnosti);
            objekat.put("cena", cena);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String URL = URLserver + "/api/radnici";
        new Thread(() -> {
            try {
                if(httpHelper.postJSONObjectFromURL(URL, objekat)) {
                    runOnUiThread(() -> {
                        Toast.makeText(getApplicationContext(),
                                "Uspesno postavljen oglas!",
                                Toast.LENGTH_LONG).show();
                    });
                } else {
                    runOnUiThread(() -> {
                        Toast.makeText(getApplicationContext(),
                                "Greska!",
                                Toast.LENGTH_LONG).show();
                    });
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }).start();
    }
}