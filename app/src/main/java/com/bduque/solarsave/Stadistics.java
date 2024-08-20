package com.bduque.solarsave;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bduque.solarsave.models.PanelCategories;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stadistics extends AppCompatActivity {
    private TableLayout tablaEstadistica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        ImageButton botonHome = findViewById(R.id.botonHome);
        tablaEstadistica = findViewById(R.id.tablaEstadistica);

        botonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Welcome.class);
                startActivity(intent);
            }
        });


        File categoriesFile = new File(getFilesDir(), "PanelCategories.txt");

        List<PanelCategories> listaCategories = leerArchivoCategories(categoriesFile);

        addElementCategories(listaCategories);
    }

    /*private void addElementCategories(List<PanelCategories> categoriasList){
        // Obtén una referencia al TableLayout en tu actividad o fragmento

        float promedioEnergiaCategorias = calcularPromedioEnergiaCategorias(categoriasList);
        float maxEnergia = calcularMaximoEnergiaCategorias(categoriasList);
        float minEnergia = calcularMinimoEnergiaCategorias(categoriasList);

        for (PanelCategories item: categoriasList) {
            // Crea una nueva fila y agrega las celdas
            TableRow row = new TableRow(this);
            //AÑADE LA INFORMACIÓN A LA CELDA 1
            TextView cell1 = new TextView(this);
            cell1.setText(item.getCategoria());

            cell1.setPadding(10, 10, 10, 10);
            cell1.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el
            // color deseado

            //AÑADE LA INFORMACIÓN A LA CELDA 2
            TextView cell2 = new TextView(this);
            cell2.setText(String.valueOf(promedioEnergiaCategorias));
            cell2.setPadding(10, 10, 10, 10);
            cell2.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el color


            //AÑADE LA INFORMACIÓN A LA CELDA 3
            TextView cell3 = new TextView(this);
            cell3.setText(String.valueOf(maxEnergia));
            cell3.setPadding(10, 10, 10, 10);
            cell3.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
            // el color

            //AÑADE LA INFORMACIÓN A LA CELDA 4
            TextView cell4 = new TextView(this);
            cell4.setText(String.valueOf(minEnergia));
            cell4.setPadding(10, 10, 10, 10);
            cell4.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
            // el color

            // Agrega las celdas a la fila
            row.addView(cell1);
            row.addView(cell2);
            row.addView(cell3);
            row.addView(cell4);
            // Agrega la fila al TableLayout
            tablaEstadistica.addView(row);
        }
    }*/

    @SuppressLint("ResourceAsColor")
    private void addElementCategories(List<PanelCategories> categoriasList) {
        // Mapa para agrupar las categorías
        Map<String, List<PanelCategories>> categoriasMap = new HashMap<>();

        // Agrupar los elementos por categoría
        for (PanelCategories item : categoriasList) {
            if (!categoriasMap.containsKey(item.getCategoria())) {
                categoriasMap.put(item.getCategoria(), new ArrayList<>());
            }
            categoriasMap.get(item.getCategoria()).add(item);
        }

        // Iterar sobre cada grupo de categorías
        for (Map.Entry<String, List<PanelCategories>> entry : categoriasMap.entrySet()) {
            String categoria = entry.getKey();
            List<PanelCategories> items = entry.getValue();

            // Calcular los valores para la categoría agrupada
            float promedioEnergia = calcularPromedioEnergiaCategorias(items);
            float maxEnergia = calcularMaximoEnergiaCategorias(items);
            float minEnergia = calcularMinimoEnergiaCategorias(items);

            // Crea una nueva fila y agrega las celdas
            TableRow row = new TableRow(this);

            // AÑADE LA INFORMACIÓN A LA CELDA 1 (Categoría)
            TextView cell1 = new TextView(this);
            cell1.setText(categoria);
            cell1.setPadding(10, 10, 10, 10);
            cell1.setBackgroundResource(R.color.white);
            cell1.setTextColor(R.color.black);

            // AÑADE LA INFORMACIÓN A LA CELDA 2 (Promedio de Energía)
            TextView cell2 = new TextView(this);
            cell2.setText(String.valueOf(promedioEnergia));
            cell2.setPadding(10, 10, 10, 10);
            cell2.setBackgroundResource(R.color.white);
            cell2.setTextColor(R.color.black);

            // AÑADE LA INFORMACIÓN A LA CELDA 3 (Máximo de Energía)
            TextView cell3 = new TextView(this);
            cell3.setText(String.valueOf(maxEnergia));
            cell3.setPadding(10, 10, 10, 10);
            cell3.setBackgroundResource(R.color.white);
            cell3.setTextColor(R.color.black);

            // AÑADE LA INFORMACIÓN A LA CELDA 4 (Mínimo de Energía)
            TextView cell4 = new TextView(this);
            cell4.setText(String.valueOf(minEnergia));
            cell4.setPadding(10, 10, 10, 10);
            cell4.setBackgroundResource(R.color.white);
            cell4.setTextColor(R.color.black);

            // Agrega las celdas a la fila
            row.addView(cell1);
            row.addView(cell2);
            row.addView(cell3);
            row.addView(cell4);

            // Agrega la fila al TableLayout
            tablaEstadistica.addView(row);
        }
    }


    private float calcularPromedioEnergiaCategorias(List<PanelCategories> categoriesList) {
        float sum = 0;
        for (PanelCategories item : categoriesList) {
            sum += item.getEnergia();
        }
        return sum / categoriesList.size();
    }

    private float calcularMaximoEnergiaCategorias(List<PanelCategories> categoriesList) {
        if (categoriesList.isEmpty()) {return 0f; // Or handle the empty list case as needed
        }
        float maxEnergia = categoriesList.get(0).getEnergia();
        for (PanelCategories item : categoriesList) {
            if (item.getEnergia() > maxEnergia) {maxEnergia = item.getEnergia();
            }
        }
        return maxEnergia;
    }

    private float calcularMinimoEnergiaCategorias(List<PanelCategories> categoriesList) {
        if (categoriesList.isEmpty()) {
            return 0f; // Or handle the empty list case as needed
        }
        float minEnergia = categoriesList.get(0).getEnergia();
        for (PanelCategories item : categoriesList) {
            if (item.getEnergia() < minEnergia) {
                minEnergia = item.getEnergia();
            }
        }
        return minEnergia;
    }

    private static List<PanelCategories> leerArchivoCategories(File archivo) {
        List<PanelCategories> listaCategorias = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String categoria = datos[0];
                String mes = datos[1];
                float energia = Float.parseFloat(datos[2]);

                PanelCategories categories = new PanelCategories(categoria, mes, energia);
                listaCategorias.add(categories);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaCategorias;
    }
}
