package com.bduque.solarsave;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bduque.solarsave.models.PanelCategories;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class addRecord extends AppCompatActivity {

    private SeekBar cantidadEnergia;
    private TextView textoseekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrecord);
        Button registrarDato = findViewById(R.id.registrarDato);
        Spinner categoria = findViewById(R.id.categorias);
        Spinner mes = findViewById(R.id.mes);
        cantidadEnergia = findViewById(R.id.cantidadEnergia);
        textoseekBar = findViewById(R.id.valorEnergia);
        textoseekBar.setText("0");
        setupSpinnerCategories();
        setupSpinnerMes();

        ImageButton botonHome = findViewById(R.id.botonHome);

        botonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Welcome.class);
                startActivity(intent);
            }
        });

        cantidadEnergia.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                textoseekBar.setText(progress + 10 + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        registrarDato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verificar que los campos no estén vacíos
                if (!categoria.getSelectedItem().toString().isEmpty() && !mes.getSelectedItem().toString().isEmpty() && !textoseekBar.getText().toString().isEmpty()) {
                    String mesBuscado = mes.getSelectedItem().toString();
                    String categoriaBuscada = categoria.getSelectedItem().toString();
                    boolean mesExiste = verificarMes(mesBuscado, categoriaBuscada);
                    if (mesExiste) {
                        // El mes ya existe en el archivo
                        Toast.makeText(addRecord.this, "El registro para el mes seleccionado ya existe", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean datosGuardados = guardarDatos(categoria.getSelectedItem().toString(), mes.getSelectedItem().toString(), textoseekBar.getText().toString());
                        if (datosGuardados) {
                            Toast.makeText(addRecord.this, "El registro se ha almacenado correctamente", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(addRecord.this, "Error al guardar el archivo",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(addRecord.this, "Por favor agregue información a los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setupSpinnerCategories(){
        Spinner spinner = findViewById(R.id.categorias);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.register_categoriesarray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    private void setupSpinnerMes(){
        Spinner spinner = findViewById(R.id.mes);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.register_mesarray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public boolean verificarMes(String mesBuscado, String categoriaBuscada) {
        File file = new File(getFilesDir(), "PanelCategories.txt");
        mesBuscado = mesBuscado.toLowerCase();
        categoriaBuscada = categoriaBuscada.toLowerCase();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String mes = linea.split(",")[1]; // Suponiendo que el mes está en la segunda columna separada por coma (,)
                String categoriab = linea.split(",")[0]; // Suponiendo que la categoría está en la primera columna separada por coma (,)
                if (mes.equalsIgnoreCase(mesBuscado) && categoriab.equalsIgnoreCase(categoriaBuscada)) {
                    bufferedReader.close();
                    return true; // El mes existe
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // El mes no existe
    }


    public boolean guardarDatos(String categoria, String mes, String energia) {
        File file = new File(getFilesDir(), "PanelCategories.txt");
        mes = mes.toLowerCase();
        try {
            // Verificar si el archivo existe
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            PanelCategories categories = new PanelCategories(categoria, mes, Float.parseFloat(energia));
            String linea = String.format(Locale.getDefault(), "%s,%s,%.2f", categories.getCategoria(), categories.getMes(), categories.getEnergia());
            bufferedWriter.write(linea);
            bufferedWriter.newLine();
            bufferedWriter.close();
            return true; // Los datos se guardaron correctamente
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // Error al guardar los datos
    }



}
