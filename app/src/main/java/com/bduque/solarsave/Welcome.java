package com.bduque.solarsave;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Welcome extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        auth = FirebaseAuth.getInstance();
        ImageButton botonLogout = findViewById(R.id.botonLogout);
        ImageButton botonCategorias = findViewById(R.id.botonCategorias);
        ImageButton botonEstadisticas = findViewById(R.id.botonEstadisticas);
        ImageButton botonBeneficios = findViewById(R.id.botonBeneficios);
        ImageButton registrarDatos = findViewById(R.id.registrarDatos);



        botonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                //auth.cl
                Toast.makeText(Welcome.this, "Hasta pronto", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        registrarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), addRecord.class);
                startActivity(intent);
            }
        });

        botonCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Categories.class);
                startActivity(intent);
            }
        });

        botonEstadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Stadistics.class);
                startActivity(intent);
            }
        });

        botonBeneficios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Benefits.class);
                startActivity(intent);
            }
        });
    }
}
