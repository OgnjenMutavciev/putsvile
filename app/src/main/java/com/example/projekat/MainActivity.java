package com.example.projekat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button usluge=findViewById(R.id.usluge);
        usluge.setOnClickListener(view -> {
            startActivity(new Intent(this, Usluge.class));
        });
        Button postavioglas=findViewById(R.id.postavioglas);
        postavioglas.setOnClickListener(view -> {
            startActivity(new Intent(this, KacenjeOglasa.class));
        });
    }
}