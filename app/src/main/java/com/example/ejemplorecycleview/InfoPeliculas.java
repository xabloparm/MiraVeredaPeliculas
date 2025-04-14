package com.example.ejemplorecycleview;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import model.Pelicula;

public class InfoPeliculas extends AppCompatActivity {

    private ImageView banderita ;
    private TextView nombreP, detalle;
    private FloatingActionButton atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.detalle_activity);

        Bundle extra = getIntent().getExtras();
        Pelicula pelicula = (Pelicula) extra.getSerializable("pais");

        banderita = findViewById(R.id.banderita);
        nombreP = findViewById(R.id.nombreP);
        detalle = findViewById(R.id.detalle);
        atras = findViewById(R.id.atras);

        nombreP.setText(pelicula.getNombre());
        detalle.setText(pelicula.getDetalle());
        Picasso.get().load(pelicula.getCartel()).into(banderita);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





    }

}
