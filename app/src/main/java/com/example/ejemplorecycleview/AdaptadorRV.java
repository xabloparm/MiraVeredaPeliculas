package com.example.ejemplorecycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import model.Pelicula;

public class AdaptadorRV extends RecyclerView.Adapter<AdaptadorRV.ViewHolder> {


    private LayoutInflater inflater;
    private List<Pelicula> peliculas;
    private View.OnClickListener onClickListener;

    //CONSTRUCTOR
    public AdaptadorRV(Context context, List<Pelicula> peliculas, View.OnClickListener onClickListener){
        this.peliculas = peliculas;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.onClickListener = onClickListener;
    }

    //CREAR EL VIEW HOLDER COMO TIPO VIEW
    @NonNull
    @Override
    public AdaptadorRV.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.holder_layout,parent,false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);
    }

    //ASOCIAR VISTA CON LOS DATOS DE LA POSICION
    @Override
    public void onBindViewHolder(@NonNull AdaptadorRV.ViewHolder holder, int position) {
        Pelicula pelicula = peliculas.get(position);
        holder.nombre.setText(pelicula.getNombre());
        Picasso.get().load(pelicula.getCartel()).into(holder.bandera);
    }

    //DEVUEKVE EL NÚMERO DE ELEMENTOS DE LA LISTA
    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView bandera;
        private TextView nombre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bandera = itemView.findViewById(R.id.imageView);
            nombre = itemView.findViewById(R.id.textView);
        }
    }
}
