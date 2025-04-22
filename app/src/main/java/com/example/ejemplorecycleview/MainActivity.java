package com.example.ejemplorecycleview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Pelicula;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Pelicula> peliculas;
    public static List<Pelicula> peliculasFavoritas = new ArrayList<>();
    private List<Pelicula> peliculasVisibles = new ArrayList<>();
    private RecyclerView recyclerView;
    private Context context = this;
    private Switch order;
    private AdaptadorRV adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        peliculas = new ArrayList<>(List.of(
                new Pelicula("Fantastic Mr. Fox",
                        "https://pics.filmaffinity.com/fantastic_mr_fox-975530423-mmed.jpg",
                        "Una divertida y original animación en stop-motion dirigida por Wes Anderson, basada en el cuento de Roald Dahl, que narra las aventuras de un astuto zorro enfrentándose a granjeros codiciosos para proteger a su familia.",
                        7.8, 7.5, 93),

                new Pelicula("End of Watch",
                        "https://pics.filmaffinity.com/end_of_watch-740750376-mmed.jpg",
                        "Un intenso y realista thriller policiaco que sigue la vida de dos agentes de Los Ángeles, mostrando su amistad y los peligros diarios a los que se enfrentan en los barrios más conflictivos.",
                        6.8, 7.2, 85),

                new Pelicula("La La Land",
                        "https://pics.filmaffinity.com/la_la_land-262021831-mmed.jpg",
                        "Un vibrante homenaje a los musicales clásicos que mezcla amor, sueños y sacrificios en una deslumbrante historia ambientada en la ciudad de Los Ángeles, protagonizada por dos jóvenes artistas.",
                        7.8, 8.0, 91),

                new Pelicula("Pulp Fiction",
                        "https://pics.filmaffinity.com/pulp_fiction-210382116-mmed.jpg",
                        "La icónica obra maestra de Quentin Tarantino que entrelaza varias historias criminales de forma no lineal, repleta de diálogos memorables, humor negro y una estética única que redefinió el cine moderno.",
                        8.6, 8.5, 92),

                new Pelicula("Se7en",
                        "https://pics.filmaffinity.com/seven_se7en-734875211-mmed.jpg",
                        "Un oscuro y perturbador thriller en el que dos detectives deben atrapar a un asesino en serie que comete atroces crímenes inspirados en los siete pecados capitales, llevándolos a un desenlace impactante.",
                        8.1, 8.3, 82),

                new Pelicula("Django Unchained",
                        "https://pics.filmaffinity.com/django_unchained-956246347-mmed.jpg",
                        "Un salvaje y estilizado western de Tarantino que sigue a un esclavo liberado en su misión de rescatar a su esposa, enfrentándose a terratenientes y cazadores de esclavos en el sur de los Estados Unidos.",
                        7.9, 8.1, 87),

                new Pelicula("Babylon",
                        "https://pics.filmaffinity.com/babylon-747027954-mmed.jpg",
                        "Un retrato excesivo y desenfrenado de la época dorada de Hollywood, donde los sueños y las ambiciones se entrelazan con el caos, la fama y la decadencia en los primeros años del cine sonoro.",
                        6.4, 6.5, 56),

                new Pelicula("Prisoners",
                        "https://pics.filmaffinity.com/prisoners-721879978-mmed.jpg",
                        "Un angustioso thriller sobre la desesperada búsqueda de dos niñas desaparecidas y hasta dónde puede llegar un padre impulsado por el dolor y la desesperanza, en una historia llena de giros y tensión.",
                        7.9, 8.0, 81),

                new Pelicula("Drive",
                        "https://pics.filmaffinity.com/drive-467825966-mmed.jpg",
                        "Un estilizado thriller de acción que sigue a un conductor profesional, misterioso y solitario, atrapado en un mundo criminal, donde la violencia brutal contrasta con una narrativa íntima y minimalista.",
                        7.6, 7.8, 93),

                new Pelicula("Whiplash",
                        "https://pics.filmaffinity.com/whiplash-344887410-mmed.jpg",
                        "Una poderosa exploración de la ambición y la presión extrema en el mundo de la música, donde un joven baterista se enfrenta a un despiadado profesor que lo empuja más allá de sus límites.",
                        8.4, 8.5, 94),

                new Pelicula("Redención",
                        "https://pics.filmaffinity.com/southpaw-186068978-mmed.jpg",
                        "Una emotiva historia de lucha, caída y redención que sigue a un boxeador campeón mientras intenta reconstruir su vida personal y profesional tras una tragedia devastadora.",
                        6.8, 7.0, 60)
        ));


        peliculasVisibles.addAll(peliculas);

        recyclerView = findViewById(R.id.recycled);
        adaptador = new AdaptadorRV(this, peliculasVisibles, this);
        recyclerView.setAdapter(adaptador);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        order = findViewById(R.id.order);

        ItemTouchHelper ith = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int posIni = viewHolder.getAdapterPosition();
                int posFin = target.getAdapterPosition();
                Pelicula pelicula = peliculasVisibles.remove(posIni);
                peliculasVisibles.add(posFin, pelicula);
                recyclerView.getAdapter().notifyItemMoved(posIni, posFin);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int posIni = viewHolder.getAdapterPosition();
                Pelicula pelicula = peliculasVisibles.get(posIni);

                if (peliculasFavoritas.contains(pelicula)) {
                    recyclerView.getAdapter().notifyItemChanged(posIni);
                    Snackbar.make(recyclerView, pelicula.getNombre() + " ya está en favoritos.", Snackbar.LENGTH_SHORT)
                            .setAction("Eliminar de favoritos", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    peliculasFavoritas.remove(pelicula);
                                    adaptador.notifyDataSetChanged();
                                }
                            }).show();
                } else {
                    peliculasFavoritas.add(pelicula);
                    recyclerView.getAdapter().notifyItemChanged(posIni);
                    Snackbar.make(recyclerView, pelicula.getNombre() + " añadida a favoritos.", Snackbar.LENGTH_LONG)
                            .setAction("Deshacer", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    peliculasFavoritas.remove(pelicula);
                                    adaptador.notifyDataSetChanged();
                                }
                            }).show();
                }
            }
        });

        ith.attachToRecyclerView(recyclerView);

        order.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                peliculasVisibles.clear();
                if (isChecked) {
                    peliculasVisibles.addAll(peliculasFavoritas);
                    order.setText("Favoritos");
                } else {
                    peliculasVisibles.addAll(peliculas);
                    order.setText("Todos");
                }
                adaptador.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View view) {
        int posicion = recyclerView.getChildAdapterPosition(view);
        Pelicula pelicula = peliculasVisibles.get(posicion);
        Intent intent = new Intent(this, InfoPeliculas.class);
        intent.putExtra("pelicula", pelicula);
        startActivity(intent);
    }
}
