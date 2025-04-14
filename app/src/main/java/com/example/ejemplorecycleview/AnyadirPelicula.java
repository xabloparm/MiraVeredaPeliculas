package com.example.ejemplorecycleview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import model.Pelicula;

public class AnyadirPelicula extends AppCompatActivity {
    private Button aceptar, cancelar;
    private EditText urlPais, nombrePais, detallepais;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.anyadir_pais);


        aceptar = findViewById(R.id.aceptar);
        cancelar = findViewById(R.id.cancelar);
        urlPais = findViewById(R.id.urlPais);
        nombrePais = findViewById(R.id.nombrePais);
        detallepais = findViewById(R.id.detallepais);

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED,intent);
                finish();
            }
        });

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent();
            Pelicula pelicula = new Pelicula(nombrePais.getText().toString(),urlPais.getText().toString(),detallepais.getText().toString());
            intent.putExtra("pais", pelicula);
            setResult(RESULT_OK,intent);
            finish();

            }
        });


    }
}
