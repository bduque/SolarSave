package com.bduque.solarsave;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Categories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        ImageButton botonHome = findViewById(R.id.botonHome);
        Button canchaCubierta = findViewById(R.id.canchaCubierta);
        Button canchaMicrofutbol = findViewById(R.id.canchaMicrofutbol);
        Button gimnasio = findViewById(R.id.gimnasio);
        Button piscinaOlimpica = findViewById(R.id.piscinaOlimpica);
        Button canchaTenis = findViewById(R.id.canchaTenis);

        botonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Welcome.class);
                startActivity(intent);
            }
        });

        canchaCubierta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), canchaCubierta.class);
                startActivity(intent);
            }
        });

        canchaMicrofutbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), canchaMicrofutbol.class);
                startActivity(intent);
            }
        });

        gimnasio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Gimnasio.class);
                startActivity(intent);
            }
        });

        piscinaOlimpica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), piscinaOlimpica.class);
                startActivity(intent);
            }
        });

        canchaTenis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), canchaTenis.class);
                startActivity(intent);
            }
        });
    }
}
