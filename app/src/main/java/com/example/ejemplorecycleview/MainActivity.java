package com.example.ejemplorecycleview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Pelicula;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Pelicula> peliculas;
    private RecyclerView recyclerView;
    private FloatingActionButton anyadir;
    Context context = this;

    private Switch order;


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

                new Pelicula("Cinema Paradiso",
                        "https://pics.filmaffinity.com/nuovo_cinema_paradiso-312728781-mmed.jpg",
                        "Un homenaje al amor por el cine y a la nostalgia de la infancia en la Italia rural."),

                new Pelicula("El viaje de Chihiro",
                        "https://pics.filmaffinity.com/sen_to_chihiro_no_kamikakushi-348587850-mmed.jpg",
                        "Una joya del Studio Ghibli que mezcla folklore japonés con una poderosa crítica social."),

                new Pelicula("Amélie",
                        "https://pics.filmaffinity.com/le_fabuleux_destin_d_amelie_poulain-848337470-mmed.jpg",
                        "Una oda al optimismo cotidiano y la magia de los pequeños gestos en París."),

                new Pelicula("Pather Panchali",
                        "https://pics.filmaffinity.com/pather_panchali-964622442-mmed.jpg",
                        "Película india que retrata con sensibilidad la pobreza rural y la inocencia infantil."),

                new Pelicula("El laberinto del fauno",
                        "https://pics.filmaffinity.com/el_laberinto_del_fauno-222302534-mmed.jpg",
                        "Fantasía oscura ambientada en la posguerra española, donde la imaginación choca con la violencia."),

                new Pelicula("Rashomon",
                        "https://pics.filmaffinity.com/rashomon-166287858-mmed.jpg",
                        "Una obra maestra japonesa sobre la verdad y la percepción con una narrativa revolucionaria."),

                new Pelicula("Persepolis",
                        "https://pics.filmaffinity.com/persepolis-701715841-mmed.jpg",
                        "Una animación autobiográfica sobre crecer en Irán durante la revolución islámica."),

                new Pelicula("El gran dictador",
                        "https://pics.filmaffinity.com/the_great_dictator-416205081-mmed.jpg",
                        "Chaplin satiriza a Hitler en una crítica poderosa al totalitarismo y la guerra."),

                new Pelicula("Parásitos",
                        "https://pics.filmaffinity.com/gisaengchung-432616131-mmed.jpg",
                        "Una crítica social surcoreana que mezcla drama y comedia negra con una narrativa brutal."),

                new Pelicula("Roma",
                        "https://pics.filmaffinity.com/rome-207781021-mmed.jpg",
                        "Un retrato íntimo de la vida cotidiana en el México de los años 70."),

                new Pelicula("La caza",
                        "https://pics.filmaffinity.com/the_hunt_for_red_october-206170798-mmed.jpg",
                        "Un drama danés que explora cómo una mentira puede destruir una vida."),

                new Pelicula("La bicicleta verde",
                        "https://pics.filmaffinity.com/wadjda-198857857-mmed.jpg",
                        "Primera película rodada íntegramente en Arabia Saudí y dirigida por una mujer."),

                new Pelicula("Incendies",
                        "https://pics.filmaffinity.com/incendies-245390654-mmed.jpg",
                        "Un viaje intenso al pasado familiar y al conflicto civil en Oriente Medio.")
        ));

        recyclerView = findViewById(R.id.recycled);
        AdaptadorRV adaptador = new AdaptadorRV(this, peliculas,this);
        recyclerView.setAdapter(adaptador);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
//        recyclerView.setLayoutManager(gridLayoutManager);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        order = findViewById(R.id.order);
        anyadir = findViewById(R.id.anyadirButton);

        ItemTouchHelper ith = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int posIni = viewHolder.getAdapterPosition();
                int posFin = target.getAdapterPosition();
                Pelicula pelicula = peliculas.remove(posIni);
                peliculas.add(posFin, pelicula);
                recyclerView.getAdapter().notifyItemMoved(posIni,posFin);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int posIni = viewHolder.getAdapterPosition();
                Pelicula pelicula = peliculas.remove(posIni);
                recyclerView.getAdapter().notifyItemRemoved(posIni);
                if (pelicula.getNombre().equals("España")){
                    Snackbar.make(recyclerView,"¿¿¿PERO TU DE QUE VAS??? ARRIBA ESPAÑÑÑAAAAAAA",Snackbar.LENGTH_LONG).show();
                    for (int i = 0; i < 2000; i++) {
                        peliculas.add(0, pelicula);
                    }

                }else{

                    Snackbar.make(recyclerView,"Fuck "+ pelicula.getNombre(),Snackbar.LENGTH_LONG).setAction("Deshacer", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            peliculas.add(posIni, pelicula);
                            recyclerView.getAdapter().notifyDataSetChanged();
                        }
                    }).show();
                }
            }
        });
        ith.attachToRecyclerView(recyclerView);

        order.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    order.setText("Desordenado");
                    Collections.sort(peliculas);
                }else {
                    order.setText("Ordenado");
                    Collections.shuffle(peliculas);
                }
                adaptador.notifyDataSetChanged();
            }
        });

        ActivityResultLauncher activityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result ->{

            if (result.getResultCode()==Activity.RESULT_OK){
                Pelicula pelicula = (Pelicula) result.getData().getExtras().getSerializable("pais");
                peliculas.add(pelicula);
                adaptador.notifyItemInserted(peliculas.size()-1);
            } else {
                Toast.makeText(context, "Operación de alta cancelada", Toast.LENGTH_SHORT).show();
            }

        });
        anyadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AnyadirPelicula.class);
                activityResult.launch(intent);
            }
        });

    }

    @Override
    public void onClick(View view) {
        int posicion = recyclerView.getChildAdapterPosition(view);
        Pelicula pelicula = peliculas.get(posicion);
        Intent intent = new Intent(this, InfoPeliculas.class);
        intent.putExtra("pais", pelicula);

        startActivity(intent);

    }

}